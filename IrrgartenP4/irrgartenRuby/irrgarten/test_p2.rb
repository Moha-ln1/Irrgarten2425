# test_p2.rb
require_relative 'monster'
require_relative 'player'
require_relative 'labyrinth'
require_relative 'game'
require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'game_state'

puts "===== Monster Test ====="
monster = Monster.new(5.0, 7.0)
monster.set_pos(2, 3)
puts "Monster Initial: #{monster}"
puts "Monster Attack: #{monster.attack}"
monster.got_wounded
puts "Monster after Wound: #{monster}"
puts "Monster is Dead: #{monster.dead?}"

puts "\n===== Player Test ====="
player = Player.new(1, 6.0, 8.0)
player.set_pos(1, 2)
puts "Player Initial: #{player}"
puts "Player Attack: #{player.attack}"
player.got_wounded
puts "Player after Wound: #{player}"
player.reset_hits
player.inc_consecutive_hits
puts "Player Defensive Energy: #{player.defensive_energy}"
puts "Player is Dead: #{player.dead?}"

puts "\n===== Labyrinth Test ====="
labyrinth = Labyrinth.new(5, 5)
puts "Initial Labyrinth:\n#{labyrinth}"
exit_pos = [4, 4]
labyrinth.set_exit(exit_pos[0], exit_pos[1])
puts "Labyrinth after setting exit:\n#{labyrinth}"

monster_pos = [2, 2]
labyrinth.add_monster(monster_pos[0], monster_pos[1], monster)
puts "Labyrinth after adding Monster:\n#{labyrinth}"

puts "Is Exit Position (4,4): #{labyrinth.exit_pos?(4, 4)}"
puts "Is Monster Position (2,2): #{labyrinth.monster_pos?(2, 2)}"
puts "Can Step on (3,3): #{labyrinth.can_step_on?(3, 3)}"
puts "Random Empty Position: #{labyrinth.random_empty_pos}"

puts "\n===== Game Test ====="
game = Game.new(2, 5, 5)
puts "Game Initial State: #{game.get_game_state}"

# Test player switching
game.next_player
puts "Game State after changing player: #{game.get_game_state}"

# Test logging
game.log_player_won
puts "Game Log after player wins:\n#{game.get_game_state[:log]}"

game.log_monster_won
puts "Game Log after monster wins:\n#{game.get_game_state[:log]}"
