require_relative 'labyrinth_character'

module Irrgarten
  class Monster < LabyrinthCharacter
    INITIAL_HEALTH = 10

    def initialize(name, intelligence, strength)
      super(name, intelligence, strength, INITIAL_HEALTH)
    end

    def attack
      Dice.intensity(@strength)
    end

    def defend(received_attack)
      defensive_energy = Dice.intensity(@intelligence)
      if defensive_energy < received_attack
        got_wounded
        dead?
      else
        false
      end
    end

    def to_s
      "Monster[Name: #{@name}, Health: #{@health}, Pos: (#{@row}, #{@col})]"
    end
    
  end
end