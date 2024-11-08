# labyrinth.rb
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
    raise NotImplementedError, "Method not implemented yet."
  end

  def put_player(direction, player)
    raise NotImplementedError, "Method not implemented yet."
  end

  def add_block(orientation, start_row, start_col, length)
    raise NotImplementedError, "Method not implemented yet."
  end

  def valid_moves(row, col)
    raise NotImplementedError, "Method not implemented yet."
  end
end
