module Irrgarten
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
    @health = 0 if @health < 0
  end

  def to_s
    "Monster[Health: #{@health}, Strength: #{@strength}, Intelligence: #{@intelligence}, Position: (#{@row}, #{@col})]"
  end

  def defend(received_attack)
    # 1.1: Verificar si el monstruo está muerto
    return true if dead?

    # 1.2: Calcular la energía defensiva del monstruo
    defensive_energy = Dice.intensity(@intelligence)

    # 1.3: Comparar la energía defensiva con el ataque recibido
    if defensive_energy < received_attack
      # El monstruo recibe una herida
      got_wounded

      # 1.4: Verificar si el monstruo ha muerto después de la herida
      dead?
    else
      false # El monstruo no muere
    end
  end
end
end