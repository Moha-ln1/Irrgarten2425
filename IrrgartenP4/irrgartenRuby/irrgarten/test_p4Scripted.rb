# frozen_string_literal: true
require_relative 'game'
require_relative 'monster'
require_relative 'player'
require_relative 'labyrinth'
require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'game_state'
require_relative '../UI/textUI'
require_relative '../Control/controller'
require_relative 'orientation'
require_relative 'directions'


module Main
  class ScriptedGameTest
    def self.main
      # Crear un laberinto de tamaño fijo y dos jugadores
      juego = Irrgarten::Game.new(2, 5, 5)

      # Simular varios turnos en el juego
      puts "Comenzando partida simulada"
      turn_count = 0

      until juego.finished? || turn_count >= 100 # Limitar a 100 turnos por prueba
        turn_count += 1
        puts "--- Turno #{turn_count} ---"

        # Cada jugador realiza su turno
        juego.players.each_with_index do |player, index|
          next if player.dead?

          puts "Turno de #{player}"

          # Movimientos válidos aleatorios
          valid_moves = [Irrgarten::Directions::UP, Irrgarten::Directions::DOWN, Irrgarten::Directions::LEFT, Irrgarten::Directions::RIGHT]
          preferred_direction = valid_moves.sample

          # Calcular dirección con preferencia aleatoria
          direction = Irrgarten::Dice.preferred_direction(preferred_direction, valid_moves, player.intelligence)

          # Simular movimiento del jugador
          puts "#{player} intenta moverse hacia #{direction}"
          juego.next_step(direction)

          # Revisar si el jugador ha muerto
          if player.dead?
            puts "#{player} ha muerto."
          end
        end

        # Verificar si algún jugador ha ganado
        if juego.finished?
          winner = juego.players.find { |p| juego.labyrinth.exit_pos?(p.row, p.col) }
          puts "El jugador #{winner} ha ganado la partida tras #{turn_count} turnos."
          break
        end
      end

      # Simular conversión a FuzzyPlayer si aún no ha habido ganadores
      juego.players.each do |player|
        next unless player.dead?

        puts "Simulando resurrección y conversión a FuzzyPlayer para #{player}"
        juego.send(:manage_resurrection)
        fuzzy_player = juego.players.find { |p| p.is_a?(Irrgarten::FuzzyPlayer) }
        puts "Jugador convertido a FuzzyPlayer: #{fuzzy_player}"
      end

      # Fin de la partida
      puts "Fin de la partida simulada tras #{turn_count} turnos."
    end
  end
end

Main::ScriptedGameTest.main

