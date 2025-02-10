require_relative 'combat_element'

module Irrgarten
  class Shield < CombatElement
    def produce_effect
      if @uses > 0
        @uses -= 1
        @effect
      else
        0
      end
    end

    def protect
      produce_effect
    end

    def to_s
      "S[#{@effect}, #{@uses}]"
    end
  end
end