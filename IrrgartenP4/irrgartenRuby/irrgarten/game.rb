module Irrgarten
  class Game
    attr_reader :labyrinth, :players, :monsters, :current_player, :log

    ROW = 0
    COL = 1
    MAX_ROUNDS = 10

    def initialize(n_players, n_rows, n_cols)
      @labyrinth = Labyrinth.new(n_rows, n_cols)
      @players = Array.new(n_players) { |i| Player.new(i, Dice.random_intelligence, Dice.random_strength) }
      @monsters = []
      @current_player_idx = Dice.who_starts(n_players)
      @current_player = @players[@current_player_idx]
      @log = ""

      configure_labyrinth
    end

    def finished?
      @labyrinth.have_a_winner?
    end

    def game_state
      {
        labyrinth: @labyrinth.to_s,
        players: @players.map(&:to_s),
        monsters: @monsters.map(&:to_s),
        current_player_idx: @current_player_idx,
        finished: finished?,
        log: @log
      }
    end

    def configure_labyrinth
      exit_pos = @labyrinth.random_empty_pos
      @labyrinth.set_exit(exit_pos[ROW], exit_pos[COL])

      @players.size.times do |i|
        monster_name = "Monster ##{i}"
        monster = Monster.new(monster_name, Dice.random_strength, Dice.random_intelligence)
        monster_pos = @labyrinth.random_empty_pos
        @labyrinth.add_monster(monster_pos[ROW], monster_pos[COL], monster)
        @monsters << monster
      end

      @labyrinth.spread_players(@players)
    end

    def next_player
      @current_player_idx = (@current_player_idx + 1) % @players.size
      @current_player = @players[@current_player_idx]
    end

    def log_player_won
      @log += "Player #{@current_player} won the combat\n"
    end

    def log_monster_won
      @log += "A monster has defeated player #{@current_player}.\n"
    end

    def log_resurrected
      @log += "Player #{@current_player} has been resurrected.\n"
    end

    def log_player_skip_turn
      @log += "Player #{@current_player} skips their turn.\n"
    end

    def log_player_no_orders
      @log += "Player #{@current_player} received no valid move orders.\n"
    end

    def log_no_monster
      @log += "No monster present for combat.\n"
    end

    def log_rounds(rounds, max)
      @log += "Combat ended after #{rounds} of #{max} rounds.\n"
    end

    def next_step(preferred_direction)
      @log = ""
      dead = @current_player.dead?

      if !dead
        direction = actual_direction(preferred_direction)

        log_player_no_orders if direction != preferred_direction

        monster = @labyrinth.put_player(direction, @current_player)

        if monster.nil?
          log_no_monster
        else
          winner = combat(monster)
          manage_reward(winner)
        end
      else
        manage_resurrection
      end

      end_game = finished?

      next_player unless end_game

      end_game
    end

    def actual_direction(preferred_direction)
      current_row = @current_player.row
      current_col = @current_player.col

      valid_moves = @labyrinth.valid_moves(current_row, current_col)

      @current_player.move(preferred_direction, valid_moves)
    end

    def combat(monster)
      rounds = 0
      winner = :player

      # 1.1: El jugador ataca primero
      player_attack = @current_player.attack
      lose = monster.defend(player_attack)

      while rounds < MAX_ROUNDS && !lose
        # 1.3: El monstruo ataca
        monster_attack = monster.attack
        lose = @current_player.defend(monster_attack)

        if !lose
          # 1.5: El jugador ataca nuevamente
          player_attack = @current_player.attack
          winner = :player
          lose = monster.defend(player_attack)
        else
          winner = :monster
        end

        rounds += 1
      end

      log_rounds(rounds, MAX_ROUNDS)

      winner
    end

    def manage_reward(winner)
      if winner == :player
        @current_player.receive_reward
        log_player_won
      else
        log_monster_won
      end
    end

    def manage_resurrection
      resurrect = Dice.resurrect_player
    
      if resurrect
        fuzzy_player = FuzzyPlayer.new(@current_player)
        replace_player(@current_player, fuzzy_player)
        log_resurrected
      else
        log_player_skip_turn
      end
    end
    
    def replace_player(old_player, new_player)
      # Reemplazar en la lista de jugadores
      idx = @players.index(old_player)
      @players[idx] = new_player if idx
    
      # Reemplazar en el laberinto
      @labyrinth.replace_player(old_player, new_player)
    end
    
  end
end
