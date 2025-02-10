module Irrgarten
  class Dice
    MAX_USES = 5
    MAX_INTELLIGENCE = 10.0
    MAX_STRENGTH = 10.0
    RESURRECT_PROB = 0.3
    WEAPONS_REWARD = 2
    SHIELDS_REWARD = 3
    HEALTH_REWARD = 5
    MAX_ATTACK = 3.0
    MAX_SHIELD = 2.0

    @generator = Random.new

    class << self
      def random_pos(max)
        @generator.rand(max)
      end

      def who_starts(nplayers)
        @generator.rand(nplayers)
      end

      def random_intelligence
        @generator.rand * MAX_INTELLIGENCE
      end

      def random_strength
        @generator.rand * MAX_STRENGTH
      end

      def resurrect_player
        @generator.rand < RESURRECT_PROB
      end

      def weapons_reward
        @generator.rand(WEAPONS_REWARD + 1)
      end

      def shields_reward
        @generator.rand(SHIELDS_REWARD + 1)
      end

      def health_reward
        @generator.rand(HEALTH_REWARD + 1)
      end

      def weapon_power
        @generator.rand * MAX_ATTACK
      end

      def shield_power
        @generator.rand * MAX_SHIELD
      end

      def uses_left
        @generator.rand(MAX_USES + 1)
      end

      def intensity(competence)
        @generator.rand * competence
      end

      def discard_element(uses_left)
        return true if uses_left == 0
        return false if uses_left >= MAX_USES
        @generator.rand > (uses_left.to_f / MAX_USES)
      end
      
      def preferred_direction(preferred, valid_moves, intelligence)
        if @generator.rand * MAX_INTELLIGENCE < intelligence
          preferred
        else
          valid_moves.sample(random: @generator)
        end
      end
    end
  end
end