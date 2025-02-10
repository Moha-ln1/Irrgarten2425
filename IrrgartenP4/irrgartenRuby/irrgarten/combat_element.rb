module Irrgarten
    class CombatElement
        attr_reader :effect, :uses
    
        def initialize(effect, uses)
          @effect = effect
          @uses = uses
        end
    
        # Método abstracto: debe ser implementado por las subclases
        def produce_effect
          raise NotImplementedError, "produce_effect debe ser implementado en subclases"
        end
    
        def discard
          Dice.discard_element(@uses)
        end
    
        def to_s
          "Element[Effect: #{@effect}, Uses: #{@uses}]"
        end
    end
end