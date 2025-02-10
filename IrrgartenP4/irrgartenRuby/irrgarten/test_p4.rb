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

module Main
  class MainTest
    def self.main
      # Configurar la vista y el juego
      vista = UI::TextUI.new

      # Crear un juego con 2 jugadores, incluyendo lógica para FuzzyPlayer
      juego = Irrgarten::Game.new(2, 5, 5)

      # Revisar la lista de jugadores para mostrar el uso de FuzzyPlayer
      juego.players.each do |player|
        puts "Inicializando jugador: #{player}"
      end

      # Crear el controlador y ejecutar el juego
      controlador = Control::Controller.new(juego, vista)
      controlador.play
    end
  end
end

Main::MainTest.main
