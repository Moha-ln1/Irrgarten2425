module Irrgarten
  class Labyrinth
    BLOCK_CHAR_OBSTACLE = 'X'
    BLOCK_CHAR_EMPTY = '-'
    BLOCK_CHAR_MONSTER = 'M'
    BLOCK_CHAR_EXIT = 'E'
    BLOCK_CHAR_COMBAT = 'C'

    ROW = 0
    COL = 1

    attr_reader :n_rows, :n_cols, :exit_row, :exit_col

    def initialize(n_rows, n_cols)
      @n_rows = n_rows
      @n_cols = n_cols
      @monsters = Array.new(n_rows) { Array.new(n_cols) }
      @players = Array.new(n_rows) { Array.new(n_cols) }
      @cells = Array.new(n_rows) { Array.new(n_cols, BLOCK_CHAR_EMPTY) }
      @exit_row = -1
      @exit_col = -1
    end

    def set_exit(row, col)
      @exit_row = row
      @exit_col = col
      @cells[row][col] = BLOCK_CHAR_EXIT
    end

    def have_a_winner?
      !@players[@exit_row][@exit_col].nil?
    end

    def to_s
      @cells.map { |row| row.join(' ') }.join("\n")
    end

    def add_monster(row, col, monster)
      if pos_ok?(row, col) && @cells[row][col] == BLOCK_CHAR_EMPTY
        @monsters[row][col] = monster
        monster.set_pos(row, col)
        @cells[row][col] = BLOCK_CHAR_MONSTER
      end
    end

    def pos_ok?(row, col)
      row.between?(0, @n_rows - 1) && col.between?(0, @n_cols - 1)
    end

    def empty_pos?(row, col)
      @cells[row][col] == BLOCK_CHAR_EMPTY
    end

    def monster_pos?(row, col)
      @cells[row][col] == BLOCK_CHAR_MONSTER
    end

    def exit_pos?(row, col)
      row == @exit_row && col == @exit_col
    end

    def combat_pos?(row, col)
      @cells[row][col] == BLOCK_CHAR_COMBAT
    end

    def can_step_on?(row, col)
      pos_ok?(row, col) && (empty_pos?(row, col) || monster_pos?(row, col) || exit_pos?(row, col))
    end

    def update_old_pos(row, col)
      if pos_ok?(row, col)
        @cells[row][col] = combat_pos?(row, col) ? BLOCK_CHAR_MONSTER : BLOCK_CHAR_EMPTY
      end
    end

    def dir_to_pos(row, col, direction)
      case direction
      when :up then [row - 1, col]
      when :down then [row + 1, col]
      when :left then [row, col - 1]
      when :right then [row, col + 1]
      else [row, col]
      end
    end

    def random_empty_pos
      loop do
        row = Dice.random_pos(@n_rows)
        col = Dice.random_pos(@n_cols)
        return [row, col] if empty_pos?(row, col)
      end
    end

    def spread_players(players)
      players.each do |player|
        pos = random_empty_pos
        put_player2d(-1, -1, pos[ROW], pos[COL], player)
      end
    end

    def put_player(direction, player)
      old_row = player.row
      old_col = player.col

      new_pos = dir_to_pos(old_row, old_col, direction)

      put_player2d(old_row, old_col, new_pos[ROW], new_pos[COL], player)
    end

    def add_block(orientation, start_row, start_col, length)
      inc_row, inc_col = 0, 0

      # Set increments based on orientation
      if orientation == :vertical
        inc_row = 1
      else
        inc_col = 1
      end

      row, col = start_row, start_col

      # Place blocks in the specified direction
      while pos_ok?(row, col) && empty_pos?(row, col) && length > 0
        @cells[row][col] = BLOCK_CHAR_OBSTACLE
        length -= 1
        row += inc_row
        col += inc_col
      end
    end

    def valid_moves(row, col)
      output = []

      output << :down if can_step_on?(row + 1, col)
      output << :up if can_step_on?(row - 1, col)
      output << :right if can_step_on?(row, col + 1)
      output << :left if can_step_on?(row, col - 1)

      output
    end

    def put_player2d(old_row, old_col, new_row, new_col, player)
      return nil unless can_step_on?(new_row, new_col)

      # Update old position to empty if it's valid
      if pos_ok?(old_row, old_col) && @players[old_row][old_col] == player
        update_old_pos(old_row, old_col)
        @players[old_row][old_col] = nil
      end

      # Check if the new position contains a monster
      if monster_pos?(new_row, new_col)
        @cells[new_row][new_col] = BLOCK_CHAR_COMBAT
        monster = @monsters[new_row][new_col]
      else
        @cells[new_row][new_col] = player.number
        monster = nil
      end

      # Update the player's new position on the board
      @players[new_row][new_col] = player
      player.set_pos(new_row, new_col)

      monster
    end
    
    def replace_player(old_player, new_player)
      @players.each_with_index do |row, r_idx|
        row.each_with_index do |cell, c_idx|
          if cell == old_player
            @players[r_idx][c_idx] = new_player
            new_player.set_pos(r_idx, c_idx)
            break
          end
        end
      end
    end
    
  end
end