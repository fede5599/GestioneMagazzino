package org.generation.italy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.generation.italy.model.Movimenti;

public class Main {

	public static void main(String[] args) {
		/*
		 * Scrivere un programma per la gestione dei movimenti di un magazzino.
		 * 
		 * All'avvio del programma inizializzare delle tabelle codice/descrizione
		 * (HashMap), in particolare:
		 * 
		 * FORNITORI (cod, nome) CLIENTI (cod, nome) PRODOTTI (cod, descrizione)
		 * tipologieMovimento (cod, descrizione) E01: acquisto da fornitore E02: reso da
		 * cliente E03: produzione interna E04: spostamento da altro magazzino U01:
		 * vendita a cliente U02: reso a fornitore U03: sostituzione in garanzia U04:
		 * spostamento a altro magazzino Il programma deve proporre un menu con le
		 * seguenti operazioni:
		 * 
		 * inserimento movimento in entrata: viene chiesto all'utente di inserire i
		 * seguenti dati: data codice prodotto quantità prodotto codice movimento
		 * riferimento (opzionale - cod fornitore (movimento E01), cod. cliente
		 * (movimento E02)) inserimento movimento in uscita: viene chiesto all'utente di
		 * inserire i seguenti dati: data codice prodotto quantità prodotto codice
		 * movimento riferimento (opzionale - cod cliente (movimento U01), cod. cliente
		 * (movimento U02)) visualizzazione movimenti in entrata (vengono mostrate le
		 * informazioni inserite nel punto 1. decodificando i codici) visualizzazione
		 * movimenti in uscita (vengono mostrate le informazioni inserite nel punto 2.
		 * decodificando i codici) giacenza prodotto: viene chiesto di inserire il
		 * codice di un prodotto e ne viene calcolata la giacenza (differenza tra le
		 * quantità in entrata e le quantità in uscita)
		 * 
		 * NB: ad ogni nuovo movimento viene assegnato un codice univoco
		 * autoincrementante
		 */

		HashMap<String, String> clienti = new HashMap<String, String>();
		HashMap<String, String> fornitori = new HashMap<String, String>();
		HashMap<String, String> prodotti = new HashMap<String, String>();
		HashMap<String, String> tipologieMovimentoE = new HashMap<String, String>();
		HashMap<String, String> tipologieMovimentoU = new HashMap<String, String>();
		ArrayList<Movimenti> listaMovimenti = new ArrayList<Movimenti>();

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yy");

		Scanner sc = new Scanner(System.in);
		Movimenti m = new Movimenti();
		String risposta, rispostaRestart, risp;
		boolean sceltaSbagliata = false, entrataSbagliata = false, uscitaSbagliata = false;
		boolean uscita = false, entrata = false, giacenza = false, ricerca = false;

		// inserimento clienti
		clienti.put("01", "Stefano Alfieri");
		clienti.put("02", "Peter Hanna");
		clienti.put("03", "Samuele Lanza");
		clienti.put("04", "Federica Zaccaro");

		// inserimento fornitori
		fornitori.put("01", "Enzo Colluto");
		fornitori.put("02", "Luca Grillo");
		fornitori.put("03", "Mario Rossi");
		fornitori.put("04", "Franco Verdi");

		// inserimento prodotti
		prodotti.put("01", "Pane");
		prodotti.put("02", "Acqua");
		prodotti.put("03", "Pasta");
		prodotti.put("04", "Fagioli");

		// inserimento movimenti Entrata
		tipologieMovimentoE.put("E01", "Aquisto dal fornitore");
		tipologieMovimentoE.put("E02", "Reso dal cliente");
		tipologieMovimentoE.put("E03", "Prodizione interna");
		tipologieMovimentoE.put("E04", "Spostamento da un'altro magazzino");

		// inserimento movimenti Uscita
		tipologieMovimentoU.put("U01", "Vendita a cliente");
		tipologieMovimentoU.put("U02", "Reso a fornitore");
		tipologieMovimentoU.put("U03", "Sostituzione in garanzia");
		tipologieMovimentoU.put("U04", "Spostamento ad un'altro magazzino");

		System.out.println("Benvenuto nel magazzino");

		// scelta del menù a tendina
		do {
			do {
				sceltaSbagliata = false;
				System.out.println(
						"Inserisci la tua azione: movimento in entrata (1), movimento in uscita (2), giacenza prodotto (3).");
				risposta = sc.nextLine();

				if (risposta.equals("1")) {
					System.out.println("Hai selezionato movimento in entrata");
					entrata = true;
				} else if (risposta.equals("2")) {
					System.out.println("Hai selezionato movimento in uscita");
					uscita = true;
				} else if (risposta.equals("3")) {
					System.out.println("Hai selezionato giacenza prodotto");
					giacenza = true;
				} else {
					System.out.println("Scelta errata, inserire nuovamente");
					sceltaSbagliata = true;
				}
			} while (sceltaSbagliata == true);

			// sessione movimento in entrata

			if (entrata == true) {
				do {
					entrataSbagliata = false;

					for (int i = 0; i <= listaMovimenti.size(); i++) {
						m = new Movimenti();

						// inizializzo MOVIMENTI ENTRATA
						System.out.print("Inserisci la data: ");
						m.data = LocalDate.parse(sc.nextLine(), df);

						System.out.print("Inserisci il codice del prodotto: ");
						m.codiceProdotto = sc.nextLine();
						System.out.print("Inserisci la quantità del prodotto in entrata: ");
						m.quantitaProdotto = sc.nextInt();
						sc.nextLine();
						m.giacenzaProdotto = m.giacenzaProdotto + m.quantitaProdotto;

						System.out.print("Inserisci il codice del movimento tra i seguenti : \n" + tipologieMovimentoE);
						m.codiceMovimento = sc.nextLine();

						if (tipologieMovimentoE.containsKey(m.codiceMovimento)) {
							listaMovimenti.add(m);
							System.out
									.println("Il movimento effettuato: " + tipologieMovimentoE.get(m.codiceMovimento));
							System.out.println("Effettuato in data: " + listaMovimenti.get(i).data.format(df));
							System.out.println("per l'articolo " + prodotti.get(m.codiceProdotto));
							entrata = false;
							break;
						} else {
							System.out.println("Movimento inserito errato, inserire nuovamente la scelta");
							entrataSbagliata = true;
						}
					}

				} while (entrataSbagliata == true);
			}
			// sessione movimento in uscita
			if (uscita == true) {
				do {
					uscitaSbagliata = false;

					for (int i = 0; i <= listaMovimenti.size(); i++) {
						m = new Movimenti();

						// inizializzo MOVIMENTI in USCITA
						System.out.print("Inserisci la data del movimento in uscita: ");
						m.data = LocalDate.parse(sc.nextLine(), df);

						System.out.print("Inserisci il codice del prodotto in uscita: ");
						m.codiceProdotto = sc.nextLine();
						do {
						System.out.print("Quantità del prodotto in uscita: ");
						m.quantitaProdotto = sc.nextInt();
						sc.nextLine();
						if (m.quantitaProdotto>m.giacenzaProdotto) {
							System.out.println("Gicenza prodotto inferiore a quella in uscita. Reinserire");
						}
						} while(m.quantitaProdotto>m.giacenzaProdotto);
						
						m.giacenzaProdotto = m.giacenzaProdotto - m.quantitaProdotto;

						System.out.print("Inserisci il codice del movimento tra i seguenti : \n" + tipologieMovimentoU);
						m.codiceMovimento = sc.nextLine();
						System.out.print("Inserisci il codice di riferimento: ");
						m.riferimento = sc.nextLine();

						if (tipologieMovimentoU.containsKey(m)) {
							listaMovimenti.add(m);
							System.out
									.println("Il movimento effettuato: " + tipologieMovimentoU.get(m.codiceMovimento));
							System.out.println("Effettuato in data: " + listaMovimenti.get(i).data.format(df));
							System.out.println("per l'articolo " + prodotti.get(m.codiceProdotto));
							uscita = false;
							break;
						} else {
							System.out.println("Movimento inserito errato, inserire nuovamente la scelta");
							uscitaSbagliata = true;
						}
					}

				} while (uscitaSbagliata == true);
				// verifica di giacenza
			} else if (giacenza == true) {
				do {
					System.out.println("Inserisci codice prodotto, per verificare giacenza");
					m.codiceProdotto = sc.nextLine();

					if (prodotti.containsKey(m.codiceProdotto)) {
						System.out.println("la giacenza dell'articolo è :" + m.giacenzaProdotto);
					}

				} while (true);
			}
			System.out.println("Vuoi ritornare al menù principale?");
			rispostaRestart=sc.nextLine();
		} while (rispostaRestart.equalsIgnoreCase("si"));

	}
}
