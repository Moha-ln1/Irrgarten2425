require 'io/console'
require_relative '../irrgarten/directions'

module UI
  class TextUI

    #https://gist.github.com/acook/4190379
    def read_char
      STDIN.echo = false
      STDIN.raw!

      input = STDIN.getc.chr
      if input == "\e"
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!

      return input
    end

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
        when "\e[A"
          puts "UP ARROW"
          output = Irrgarten::Directions::UP
          got_input = true
        when "\e[B"
          puts "DOWN ARROW"
          output = Irrgarten::Directions::DOWN
          got_input = true
        when "\e[C"
          puts "RIGHT ARROW"
          output = Irrgarten::Directions::RIGHT
          got_input = true
        when "\e[D"
          puts "LEFT ARROW"
          output = Irrgarten::Directions::LEFT
          got_input = true
        when "\u0003"
          puts "CONTROL-C"
          got_input = true
          exit(1)
        else
          #Error
        end
      end
      output
    end

    # Método para mostrar el estado del juego
    def show_game(game_state)
      # Mostrar el laberinto
      puts "===== Labyrinth ====="
      puts game_state[:labyrinth]

      # Mostrar la lista de jugadores
      puts "\n===== Players ====="
      puts game_state[:players]

      # Mostrar la lista de monstruos
      puts "\n===== Monsters ====="
      puts game_state[:monsters]

      # Mostrar el jugador actual
      puts "\nCurrent Player: Player ##{game_state[:current_player_idx]}"

      # Mostrar el estado del juego (si hay un ganador)
      if game_state[:finished]
        puts "\nThere is a winner!"
      else
        puts "\nThe game is still ongoing."
      end

      # Mostrar el registro de eventos del juego
      puts "\n===== Game Log ====="
      puts game_state[:log]
    end
  end
end
