# frozen_string_literal: true
require_relative 'game'
require_relative 'monster'
require_relative 'player'
require_relative 'labyrinth'
require_relative 'game'
require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'game_state'
require_relative '../UI/textUI'
require_relative '../Control/controller'

module Main
  class MainTest
    def self.main
      vista=UI::TextUI.new
      juego = Irrgarten::Game.new(2,5,5)
      controlador=Control::Controller.new(juego,vista)
      controlador.play
    end
  end
end

Main::MainTest.main