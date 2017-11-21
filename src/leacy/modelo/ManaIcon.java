package leacy.modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ManaIcon {

	
	public ManaIcon(){
		//Se carga la imagen
		paisajes = ( new ImageIcon( "paisaje.jpg" ) ).getImage();
		//Se asigna la posicion en x dentro del JPanel
		posicion_x = 0;
		//Se asigna la posicion en y dentro del JPanel
		posicion_y = 20;
		//Se asigna el valor de los segundos
		segundos =  5;
		//se asigna el valor del frame
		frame = 0;
	}
	
	
	//-------------------- Se utiliza para pintar la imagen en el JPanel -------------------//
	public void paint( Graphics g ){
		//Pinta con azul un rectangulo
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 301, 20);
		//Escribe un texto de color blanco
		g.setColor(Color.WHITE);
		g.drawString(segundos+"", 145, 15);
		
		//Segun el frame calcula en la posicion en donde se encuentra dentro de la imagen
		int frameX = ( frame % 2 ) * ancho;
		int frameY = ( frame / 2) * alto;
		
		//Dibuja el frame especificado en la posicion indicada
		
	}
	//--------------------------------------------------------------------------------------//
	
	//Posicion en x, dentro del JPanel
	int posicion_x;
	//Posicion en y, dentro del JPanel
	int posicion_y;
	//Ancho de los frames, no de la imagen en general
	int ancho = 301;
	//Alto de los frames, no de la imagen en general
	int alto = 301;
	//Frame que hay en las imagenes (en este caso 4)
	int frame;
	//Segundos en que aparece cada imagen
	int segundos;
	//Imagen a cargar de 603x603
	Image paisajes;
	
}