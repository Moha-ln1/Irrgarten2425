module Irrgarten
  class Player
  MAX_HEALTH = 10
  INIT_HEALTH = 5
  MAX_SHIELDS = 3
  MAX_WEAPONS = 2
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
    @health = 0 if @health < 0
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
    manage_hit(received_attack)
  end

  def move(direction, valid_moves)
    # 1.1: Obtener el número de movimientos válidos
    size = valid_moves.size

    # 1.2: Si hay movimientos válidos y la dirección preferida no es una de ellos
    if size > 0 && !valid_moves.include?(direction)
      return valid_moves.first # 1.3: Obtener el primer movimiento válido
    end

    direction # 1.5: Retornar la dirección preferida
  end

  def receive_reward
    # 1.1: Obtener la recompensa de armas
    weapons_reward = Dice.weapons_reward

    # 1.2: Obtener la recompensa de escudos
    shields_reward = Dice.shields_reward

    # 1.3: Crear y recibir nuevas armas
    weapons_reward.times do
      receive_weapon(new_weapon)
    end

    # 1.4: Crear y recibir nuevos escudos
    shields_reward.times do
      receive_shield(new_shield)
    end

    # 1.5: Agregar salud adicional
    extra_health = Dice.health_reward
    @health += extra_health
  end

  def receive_weapon(weapon)
    # 1.1: Verificar las armas existentes y remover las que deben ser descartadas
    @weapons.reject!(&:discard)

    # 1.2: Verificar si se puede agregar una nueva arma
    @weapons << weapon if @weapons.size < MAX_WEAPONS
  end

  def receive_shield(shield)
    # 1.1: Verificar los escudos existentes y remover los que deben ser descartados
    @shields.reject!(&:discard)

    # 1.2: Verificar si se puede agregar un nuevo escudo
    @shields << shield if @shields.size < MAX_SHIELDS
  end

  def manage_hit(received_attack)
    # 1.1: Calcular la energía defensiva del jugador
    defense = defensive_energy

    # 1.2: Comparar la defensa con el ataque recibido
    if defense < received_attack
      got_wounded
      inc_consecutive_hits
    else
      reset_hits
    end

    # 1.3: Verificar si el jugador está muerto o ha alcanzado el límite de golpes consecutivos
    if @consecutive_hits == HITS2LOSE || dead?
      reset_hits
      true # El jugador pierde
    else
      false # El jugador no pierde
    end
  end
  end
end
