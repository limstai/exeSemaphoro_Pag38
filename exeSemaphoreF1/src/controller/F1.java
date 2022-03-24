package controller;

import java.util.concurrent.Semaphore;

public class F1 extends Thread {
	private int idCarro;
	private int idEscuderia;
	private int tempoVolta;

	static int indice;

	private Semaphore Pista;
	private Semaphore Table;
	private Semaphore Escuderia;

	public F1(int idCarro, int idEscuderia, Semaphore pista, Semaphore tabela, Semaphore escuderia) {
		this.Escuderia = escuderia;
		this.idCarro = idCarro;
		this.idEscuderia = idEscuderia;
		this.Pista = pista;
		this.Table = tabela;
	}

	@Override
	public void run() {
		try {
			Pista.acquire();
			Escuderia.acquire();
			CarroRun();
			Table.acquire();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			Pista.release();
			Escuderia.release();
			Table.release();
		}
	}

	private void CarroRun() {
		double tempoInicial;
		double tempoFinal;
		double tempoTotal;
		int distanciaPista = 1000;
		int distanciaTotal = 0;
		int gerador;

		for (int numero_voltas = 1; numero_voltas <= 3; numero_voltas++) {
			tempoInicial = System.nanoTime();
			while (distanciaTotal < distanciaPista) {
				gerador = (int) ((Math.random() * 101) + 100);
				distanciaTotal += gerador;
			}
			tempoFinal = System.nanoTime();
			tempoTotal = tempoFinal - tempoInicial;
			tempoTotal = tempoTotal / Math.pow(10, 9);
			System.out.println("O carro #" + idCarro + " da Escuderia " + idEscuderia + " fez a " + numero_voltas
					+ "º volta em " + tempoTotal);

			if (tempoTotal < tempoVolta) {
				tempoVolta += (int) tempoTotal;

			}

			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}