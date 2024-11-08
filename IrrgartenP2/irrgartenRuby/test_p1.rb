require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'game_state'

class TestP1
  def self.main
    # Prueba de Weapon
    weapon = Weapon.new(2.0, 5)
    puts "Weapon initial: #{weapon}"
    puts "Weapon Attack: #{weapon.attack}"
    puts "Weapon after attack: #{weapon}"
    6.times do |i|
      puts "Weapon Attack Attempt #{i + 1}: #{weapon.attack}"
    end
    puts "Weapon final: #{weapon}"
    puts "Weapon Discard: #{weapon.discard}"

    # Prueba de Shield
    shield = Shield.new(3.0, 4)
    puts "\nShield initial: #{shield}"
    puts "Shield Protect: #{shield.protect}"
    puts "Shield after protect: #{shield}"
    5.times do |i|
      puts "Shield Protect Attempt #{i + 1}: #{shield.protect}"
    end
    puts "Shield final: #{shield}"
    puts "Shield Discard: #{shield.discard}"

    # Prueba de Dice
    puts "\nDice Tests:"
    100.times do |i|
      puts "Resurrect Player Attempt #{i + 1}: #{Dice.resurrect_player}"
    end

    puts "Random Position: #{Dice.random_pos(10)}"
    puts "Who Starts: #{Dice.who_starts(4)}"
    puts "Random Intelligence: #{Dice.random_intelligence}"
    puts "Random Strength: #{Dice.random_strength}"
    puts "Weapons Reward: #{Dice.weapons_reward}"
    puts "Shields Reward: #{Dice.shields_reward}"
    puts "Health Reward: #{Dice.health_reward}"
    puts "Weapon Power: #{Dice.weapon_power}"
    puts "Shield Power: #{Dice.shield_power}"
    puts "Uses Left: #{Dice.uses_left}"
    puts "Intensity: #{Dice.intensity(5.0)}"
    puts "Discard Element (uses = 3): #{Dice.discard_element(3)}"
    puts "Discard Element (uses = 0): #{Dice.discard_element(0)}"
    puts "Discard Element (uses = 5): #{Dice.discard_element(5)}"

    # Prueba de GameState
    game_state = GameState.new("Labyrinth", "Player1", "Monster1", 0, false, "Game started")
    puts "\nGameState:"
    puts "Labyrinth: #{game_state.labyrinth}"
    puts "Players: #{game_state.players}"
    puts "Monsters: #{game_state.monsters}"
    puts "Current Player: #{game_state.current_player}"
    puts "Is Winner: #{game_state.winner}"
    puts "Log: #{game_state.log}"
  end
end

# Ejecutar el programa de prueba
TestP1.main
