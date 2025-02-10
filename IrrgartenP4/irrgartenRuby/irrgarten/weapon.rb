require_relative 'combat_element'

module Irrgarten
  class Weapon < CombatElement

    def produce_effect
      if @uses > 0
        @uses -= 1
        @effect
      else
        0
      end
    end

    def attack
      produce_effect
    end

    def to_s
      "W[#{@effect}, #{@uses}]"
    end
    
  end
end