# player.rb
class Player
  MAX_HEALTH = 10
  INIT_HEALTH = 5
  MAX_SHIELDS = 3
  HITS2LOSE = 3

  attr_reader :name, :number, :health, :pos, :col, :intelligence, :strength

  def initialize(number, intelligence, strength)
    @name = "Player ##{number}"
    @number = number.to_s
    @health = INIT_HEALTH
    @intelligence = intelligence
    @strength = strength
    @pos = -1
    @col = -1
    @weapons = []
    @shields = []
    @consecutive_hits = 0
  end

  def resurrect
    @weapons.clear
    @shields.clear
    @health = INIT_HEALTH
    @consecutive_hits = 0
  end

  def set_pos(row, col)
    @pos = row
    @col = col
  end

  def dead?
    @health <= 0
  end

  def attack
    @strength + sum_weapons
  end

  def new_weapon
    Weapon.new(Dice.weapon_power, Dice.uses_left)
  end

  def new_shield
    Shield.new(Dice.shield_power, Dice.uses_left)
  end

  def defensive_energy
    @intelligence + sum_shields
  end

  def reset_hits
    @consecutive_hits = 0
  end

  def got_wounded
    @health -= 1
  end

  def inc_consecutive_hits
    @consecutive_hits += 1
  end

  def sum_weapons
    @weapons.sum(&:attack)
  end

  def sum_shields
    @shields.sum(&:protect)
  end

  def to_s
    "Player[#{@name}, Health: #{@health}, Position: (#{@pos}, #{@col})]"
  end

  def defend(received_attack)
    raise NotImplementedError, "Method not implemented yet."
  end

  def move(direction, valid_moves)
    raise NotImplementedError, "Method not implemented yet."
  end

  def receive_reward
    raise NotImplementedError, "Method not implemented yet."
  end

  def receive_weapon(weapon)
    raise NotImplementedError, "Method not implemented yet."
  end

  def receive_shield(shield)
    raise NotImplementedError, "Method not implemented yet."
  end

  def manage_hit(received_attack)
    raise NotImplementedError, "Method not implemented yet."
  end
end
