# monster.rb
class Monster
  INITIAL_HEALTH = 3

  attr_reader :health, :strength, :intelligence, :row, :col

  def initialize(strength, intelligence)
    @health = INITIAL_HEALTH
    @strength = strength
    @intelligence = intelligence
    @row = -1
    @col = -1
  end

  def dead?
    @health <= 0
  end

  def attack
    Dice.intensity(@strength)
  end

  def set_pos(row, col)
    @row = row
    @col = col
  end

  def got_wounded
    @health -= 1
  end

  def to_s
    "Monster[Health: #{@health}, Strength: #{@strength}, Intelligence: #{@intelligence}, Position: (#{@row}, #{@col})]"
  end

  def defend(received_attack)
    raise NotImplementedError, "Method not implemented yet."
  end
end
