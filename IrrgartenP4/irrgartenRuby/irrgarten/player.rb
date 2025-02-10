require_relative 'labyrinth_character'

module Irrgarten
  class Player < LabyrinthCharacter
    MAX_HEALTH = 10
    INITIAL_HEALTH = 5
    MAX_SHIELDS = 3
    MAX_WEAPONS = 2

    attr_reader :number

    def initialize(number, intelligence, strength)
      @number = number
      super("Player ##{number}", intelligence, strength, MAX_HEALTH)
      @weapons = []
      @shields = []
    end

    def copy_from(other)
      super(other)
      @weapons = other.weapons.dup
      @shields = other.shields.dup
    end
    
    def attack
      @strength + sum_weapons
    end
    def manage_hit(received_attack)
      defense = defensive_energy
      if defense < received_attack
        got_wounded
        inc_consecutive_hits
      else
        reset_hits
      end
    
      dead? || @consecutive_hits >= HITS2LOSE
    end
    
    def defend(received_attack)
      defense = defensive_energy
      if defense < received_attack
        got_wounded
        true
      else
        false
      end
    end

    def receive_reward
      # Recompensas de armas
      weapons_reward = Dice.weapons_reward
      weapons_reward.times do
        receive_weapon(new_weapon)
      end
    
      # Recompensas de escudos
      shields_reward = Dice.shields_reward
      shields_reward.times do
        receive_shield(new_shield)
      end
    
      # Recompensa de salud
      extra_health = Dice.health_reward
      @health += extra_health
      @health = MAX_HEALTH if @health > MAX_HEALTH
    end
    
    def move(preferred_direction, valid_moves)
      # Si la dirección preferida está entre los movimientos válidos, la utilizamos.
      if valid_moves.include?(preferred_direction)
        preferred_direction
      else
        # Si no, seleccionamos el primer movimiento válido.
        valid_moves.first
      end
    end
    def new_weapon
      Weapon.new(Dice.weapon_power, Dice.uses_left)
    end
    
    def new_shield
      Shield.new(Dice.shield_power, Dice.uses_left)
    end
    
    def receive_weapon(weapon)
      @weapons << weapon if @weapons.size < 2
    end

    def receive_shield(shield)
      @shields << shield if @shields.size < 3
    end

    def sum_weapons
      @weapons.sum(&:attack)
    end
    
    def sum_shields
      @shields.sum(&:protect)
    end

    def defensive_energy
      @intelligence + @shields.sum(&:protect)
    end

    def reset_hits
      @consecutive_hits = 0
    end
    
    def inc_consecutive_hits
      @consecutive_hits += 1
    end
    def to_s
      "Player[Name: #{@name}, Health: #{@health}, Pos: (#{@row}, #{@col})]"
    end
    
  end
end
