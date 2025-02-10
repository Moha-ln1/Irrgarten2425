module Irrgarten
    # Clase base que generaliza Player y Monster
    class LabyrinthCharacter
      attr_reader :name, :intelligence, :strength, :health, :row, :col
  
      def initialize(name, intelligence, strength, health)
        @name = name
        @intelligence = intelligence
        @strength = strength
        @health = health
        @row = -1
        @col = -1
      end

      def copy_from(other)
        @name = other.name
        @intelligence = other.intelligence
        @strength = other.strength
        @health = other.health
        @row = other.row
        @col = other.col
      end

      def dead?
        @health <= 0
      end
  
      def set_pos(row, col)
        @row = row
        @col = col
      end
  
      def to_s
        "#{self.class}[Name: #{@name}, Health: #{@health}, Position: (#{@row}, #{@col})]"
      end
  
      protected
  
      def got_wounded
        @health -= 1
        @health = 0 if @health < 0
      end
    end
end