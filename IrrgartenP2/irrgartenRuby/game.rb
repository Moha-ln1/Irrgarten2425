# game.rb
class Game
  attr_reader :labyrinth, :players, :monsters, :current_player, :log

  ROW = 0
  COL = 1 #Quitar despues de configurar el configurarLabyrinth


  def initialize(n_players, n_rows, n_cols)
    @labyrinth = Labyrinth.new(n_rows, n_cols)
    @players = Array.new(n_players) { |i| Player.new(i, Dice.random_intelligence, Dice.random_strength) }
    @monsters = []
    @current_player_idx = Dice.who_starts(n_players)
    @current_player = @players[@current_player_idx]
    @log = ""

    #configure_labyrinth
  end

  def finished?
    @labyrinth.have_a_winner?
  end

  def get_game_state
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

    @players.size.times do
      monster = Monster.new(Dice.random_strength, Dice.random_intelligence)
      monster_pos = @labyrinth.random_empty_pos
      @labyrinth.add_monster(monster_pos[ROW], monster_pos[COL], monster)
      @monsters << monster
    end
  end

  def next_player
    @current_player_idx = (@current_player_idx + 1) % @players.size
    @current_player = @players[@current_player_idx]
  end

  def log_player_won
    @log += "Player #{@current_player} has reached the exit and won the game!\n"
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
    raise NotImplementedError, "Method not implemented yet."
  end

  def actual_direction(preferred_direction)
    raise NotImplementedError, "Method not implemented yet."
  end

  def combat(monster)
    raise NotImplementedError, "Method not implemented yet."
  end

  def manage_reward(winner)
    raise NotImplementedError, "Method not implemented yet."
  end

  def manage_resurrection
    raise NotImplementedError, "Method not implemented yet."
  end
end
