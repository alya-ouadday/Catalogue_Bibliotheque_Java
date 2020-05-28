package projet.application;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import projet.data.FileReader;
import projet.exceptions.formatEANException;
import projet.exceptions.formatISBNException;
import projet.exceptions.inscriptionException;
import projet.exceptions.nonDispoException;
import projet.exceptions.quotaException;
import projet.classesProjet.*;

public class Main 
{
	public static void afficheMenu() {
		System.out.println("Choisissez le numéro de votre commande : ");
		System.out.println("1 - ajouter une nouvelle bibliothèque au reseau ");
		System.out.println("2 - ajouter document");
		System.out.println("3 - ajouter nouvel utilisateur au reseau");
		System.out.println("4 - afficher l'ensemble des document ");
		System.out.println("5 - afficher les document d'une série");
		System.out.println("6 - afficher les documents d'un auteur");
		System.out.println("7 - afficher un livre par son ISBN");
		System.out.println("8 - afficher un document par son EAN");
		System.out.println("9 - afficher le nombre de doc entre deux date");
		System.out.println("10 - emprunter un document");
		System.out.println("11 - rendre un document");
		System.out.println("12 - inscrire un utilisateur");
		System.out.println("13 - afficher un utilisateur");
		System.out.println("14 - afficher les utilisateur du reseau");
		System.out.println("15 - Quitter");

	}
	public static void menu(Reseau reseau) {
		Scanner sc = new Scanner(System.in);
		
		boolean marche = true;
		int commande = 0;
		int typeAction = 0; //si egal à 0 alors on fait les action sur le réseau sinon on les fait sur une bibliotheque.
		while(marche) {
			//si on est dans le menu
			if(commande ==0) {
				afficheMenu();
				System.out.println("Veuillez choisir une commande :");
				String str = sc.nextLine();
				System.out.println("Vous avez choisis : " + str);
				try {
					commande = Integer.parseInt(str);
					if(commande < 0 || commande > 16) {
						System.out.println("Veuillez entrer un numéro de commande entre 1 et 15 \n "
								+ " Appuyer sur entrer pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
				}catch(NumberFormatException e) {
					System.out.println("Veuillez entrer un numéro de commande entre 1 et 15 \n "
							+ " Appuyer sur entrer pour revenir au menu");
					str = sc.nextLine();
					commande = 0; 
				}
			}
			//si commande 1
			else if(commande ==1) {
				
				System.out.println("Veuillez saisir le nom de la nouvelle bibliotheque :");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine().toLowerCase();
				if(str.isEmpty()) {
					commande = 0;
				}else {
				reseau.addBiblio(new Bibliotheque(str,reseau));
				System.out.println("La nouvelle bibliothèque " + str + " a ete ajoutee au reseau.");
				System.out.println("Taper sur entrer pour revenir au menu");
				str = sc.nextLine();
				commande=0;
				}	
			}
			//si commande 2
			else if(commande ==2) {
				System.out.println("Entrer l'ean de votre document:");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
					String ean =str;
					System.out.println("Entrer l'ISBN de votre document ou faites entre s'il n'en a pas:");
					String isbn = sc.nextLine();
					System.out.println("Entrer le titre de votre document :");
					String title = sc.nextLine();
					System.out.println("Entrer l'editeur de votre document :");
					String publisher= sc.nextLine();
					System.out.println("Entrer le titre de serie de votre document :");
					String seriesTitle= sc.nextLine();
					System.out.println("Entrer le numero dans sa serie de votre document :");
					Integer seriesNumber= 0; 
					try {
					seriesNumber= Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						seriesNumber = 0; 
					}
					
					System.out.println("Entrer le nom de l'auteur de votre document :");
					String authorName= sc.nextLine();
					System.out.println("Entrer le prenom de l'auteur de votre document :");
					String authorSurname= sc.nextLine();
					System.out.println("Entrer le type de votre document :");
					String type= sc.nextLine();
					System.out.println("Entrer la date de votre document :");
					
					int date= Integer.parseInt(sc.nextLine());
					System.out.println("Entrer le nombre total de copies de votre document :");
					int totalCopies= Integer.parseInt(sc.nextLine());
					
					Document document = FileReader.createDoc(reseau, isbn, ean, title, publisher, seriesTitle, seriesNumber, authorName, authorSurname, type, date, totalCopies);
					try {
						reseau.addNewDocument(document);
						System.out.println("le document suivant a ete ajoute au reseau : \n"+document);
					} catch (formatISBNException | formatEANException e) {
						e.printStackTrace();
					}
					}catch(NumberFormatException e) {
						e.printStackTrace();
					}
					
					
					System.out.println("Taper sur entrer pour revenir au menu");
					str = sc.nextLine();
					commande=0;
					
				}	
			}
			//si commande 3
			else if(commande ==3) {
				System.out.println("Entrer le nom du nouvel utilisateur :");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					String name = str;
					System.out.println("Entrer son quota :");
					int quota = 5;//quota par defaut
					str = sc.nextLine();
					try {
						quota = Integer.parseInt(str);
						Utilisateur user= new Utilisateur(name, quota);
						reseau.getListeUtilisateur().put(user.getId(), user);
						System.out.println(name+" a ete ajouté au reseau avec l'id :"+user.getId()+" et un quota de "+quota);
						System.out.println("Taper sur entrer pour revenir au menu");
						str = sc.nextLine();	
						commande=0;
					}catch(NumberFormatException e1) {
						System.out.println("Veuillez entrer un nombre pour quota  \n "
								+ " Appuyer sur entrer pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
				
				}	
			}
			//si commande 4: afficher l'ensemble des documents 
			else if(commande ==4) {
				System.out.println("Saisir 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
					typeAction = Integer.parseInt(str);
					if(typeAction != 1 && typeAction != 0) {
						System.out.println("Erreur. Il faut saisir 0 ou 1");
						System.out.println("Taper sur entrée pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
					}catch(NumberFormatException e) {
						System.out.println("Erreur. Il faut saisir 0 ou 1");
						System.out.println("Taper sur entrée pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
					if(typeAction == 0 && commande != 0) {
						reseau.ShowAllDocuments();
					}else if(typeAction == 1 && commande != 0 ) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine().toLowerCase();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							reseau.getListeBiblio().get(str).ShowAllDocuments();
							System.out.println("Taper sur entrer pour revenir au menu");
							str = sc.nextLine();
							commande=0;
						}
					}
					
				}	
			}
			//si commande 5: afficher les documents d'une serie 
			else if(commande ==5) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					
					if(typeAction == 0 && commande != 0 ) {
						System.out.println("Entrer le nom d'une serie");
						str = sc.nextLine();
						reseau.searchSerie(str);
					}else if(typeAction == 1 && commande !=0 ) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine().toLowerCase();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
			
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else {
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Entrer le nom d'une serie");
							str = sc.nextLine();
							bibli.searchSerie(str);
							System.out.println("Taper entrée pour retourner au menu "); 
							str = sc.nextLine(); 
							commande = 0;
						}
						
						
						
						
					}
					 
				}	
			}
			//si commande 6: afficher les documents d'un auteur 
			else if(commande ==6) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0 && commande != 0) {
						System.out.println("Nous allons vous demander le nom et le prenom de l'auteur");
						System.out.println("d'abord entrer le nom de l'auteur :");
						System.out.println("Taper sur entrée si vous ne connaissez pas le nom");
						String nom = sc.nextLine();
						System.out.println("maintenant entrer le prenom de l'auteur :");
						System.out.println("Taper sur entrée si vous ne connaissez pas le prenom");
						String prenom = sc.nextLine();
						if(!nom.isEmpty() && !prenom.isEmpty()) {
							reseau.searchDocumentsAuthor(nom+ " "+ prenom);
						}else if(!nom.isEmpty() && prenom.isEmpty()) {
							reseau.searchDocumentsAuthorName(nom);
						}else if (nom.isEmpty() && !prenom.isEmpty()) {
								reseau.searchDocumentsAuthorSurname(prenom);}
					}else if(typeAction == 1 && commande != 0) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine().toLowerCase();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Nous allons vous demander le nom et le prenom de l'auteur");
							System.out.println("d'abord saisir le nom de l'auteur :");
							System.out.println("Taper sur entrée si vous ne connaissez pas le nom");
							String nom = sc.nextLine();
							System.out.println("maintenant saisir le prenom de l'auteur :");
							System.out.println("Taper sur entrée si vous ne connaissez pas le prenom");
							String prenom = sc.nextLine();
							if(!nom.isEmpty() && !prenom.isEmpty()) {
								bibli.searchDocumentsAuthor(nom + " " + prenom);
							}else if(!nom.isEmpty() && prenom.isEmpty()) {
								bibli.searchDocumentsAuthorName(nom);
							}else bibli.searchDocumentsAuthorSurname(prenom);
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande=0;
						}
						
					}	
				}
			}
			//si commande 7: afficher un livre par isbn 
			else if(commande ==7) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0 && commande != 0) {
						System.out.println("Entrer l'ISBN du livre que vous cherchez :");
						str = sc.nextLine();
						try {
							reseau.searchISBN(str);
						} catch (formatISBNException e) {
							e.printStackTrace();
						}
					}else if(typeAction == 1 && commande != 0) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine().toLowerCase();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Entrer l'ISBN du livre que vous cherchez :");
							str = sc.nextLine();
							try {
								bibli.searchISBN(str);
							} catch (formatISBNException e) {
								e.printStackTrace();
							}
							System.out.println("Taper sur entrée pour retourner au menu");
							str = sc.nextLine();
							commande=0;
						}
					}
				
				}	
			}
			//si commande 8: afficher un doc par ean 
			else if(commande ==8) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0 && commande != 0) {
						System.out.println("Entrer l'EAN du document que vous cherchez :");
						str = sc.nextLine();
						try {
							reseau.searchEAN(str);
						} catch (formatEANException e) {
							e.printStackTrace();
						}
					}else if(typeAction == 1 && commande != 0) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine().toLowerCase();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Entrer l'EAN du document que vous cherchez :");
							str = sc.nextLine();
							try {
								bibli.searchEAN(str);
							} catch (formatEANException e) {
								e.printStackTrace();
							}
							System.out.println("Taper sur entrer pour revenir au menu");
							str = sc.nextLine();
							commande=0;
						}
					}
					
					
				}	
			}
			//si commande 9: afficher le nombre de doc entre deux dates 
			else if(commande ==9) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						if(commande != 0) {
						System.out.println("Nous allons vous demander la période sur laquelle vous souhaiter connaitre le nombre de document");
						System.out.println("d'abord l'annee à partir de laquelle vous voulez compter :");
						String debut = sc.nextLine();
						System.out.println("maintenant l'annee jusqu'à laquelle vous voulez compter :");
						String end = sc.nextLine();
						int debutNumber = 0;
						int endNumber = 0;
						try {
							debutNumber = Integer.parseInt(debut);
							endNumber = Integer.parseInt(end);
							if(debutNumber < 0 || endNumber > 2020 || debutNumber > 2020 || endNumber < 0) {
								debutNumber = 0; 
								endNumber = 0; 
								System.out.println("Vos dates sont erronees");
							}
							else {
								if(typeAction == 0) {
									reseau.searchNumberPeriod(debutNumber, endNumber);
								}
								else {
									System.out.println("Entrer le nom d'une bibliotheque");
									str = sc.nextLine().toLowerCase();
									while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
										System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
										str = sc.nextLine();
									}
									if (str.isEmpty()) {
										commande = 0;
									}
									else{
										Bibliotheque bibli = reseau.getListeBiblio().get(str);
									
										bibli.searchNumberPeriod(debutNumber, endNumber);
									}
								}
							}
						}catch(NumberFormatException e ){
							System.out.println("Vos dates sont erronees");
							debutNumber = 0; 
							endNumber = 0; 
						}

						System.out.println("Taper sur entrer pour revenir au menu");
						str = sc.nextLine();
						commande=0;
						}
						
				}
			}
			
			//si commande 10: emprunter un document 
			else if(commande ==10) {
				System.out.println("Entrer 0 pour faire emprunter un utilisateur ou 1 pour faire emprunter une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					Utilisateur user = null; 
					Bibliotheque biblioH = null; 
					if(typeAction == 0 && commande != 0) {
						System.out.println("Entrer l'id de l'utilisateur :");
						str = sc.nextLine();
						try {
							Integer id = Integer.parseInt(str);
							if(reseau.getListeUtilisateur().containsKey(id)){
								 user = reseau.getListeUtilisateur().get(id);
							}else {
								System.out.println("cet utilisateur n'est pas dans le réseau \n Taper entrer pour revenir au menu ");
								str = sc.nextLine();
								commande = 0; 
							}
						}catch(NumberFormatException e) {
							System.out.println("Veuillez entrer un nombre pour l'id de l'utilisateur \n Taper entrer pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
				
					}
					else if(typeAction == 1 && commande != 0) {
						System.out.println("Entrer le nom de la bibliotheque qui veut emprunter");
						str = sc.nextLine().toLowerCase();
						if(reseau.getListeBiblio().containsKey(str)){
							biblioH = reseau.getListeBiblio().get(str);
						}else {
							System.out.println("cette bibliotheque n'est pas valide \n Taper entrer pour revenir au menu ");
									str = sc.nextLine();
									commande = 0; 
							}
					}
						if(commande != 0) {
									
							System.out.println("Entrer le nom d'une bibliotheque dans laquelle emprunter");
							str = sc.nextLine().toLowerCase();
							if(reseau.getListeBiblio().containsKey(str)) {
								Bibliotheque bibli = reseau.getListeBiblio().get(str);
								
								System.out.println("Entrer le EAN ou l'ISBN du document que vous voulez emprunter");
								str = sc.nextLine();
								Document docu = null;
								try {
									docu = bibli.searchEAN(str);
								} catch (formatEANException e) {
									e.printStackTrace();
								}
								if(docu!=null) {
									try {
										if(typeAction == 0) {
										user.emprunter(bibli, docu);
										System.out.println("emprunt reussi \n" + user.toString());
										}
										else {
											System.out.println("Avant: nombre de copie " + bibli.getName()+ ": "+
													bibli.getListeCopieDoc().get(docu.getEAN()) + " nombre de copie " + biblioH.getName()
													+" : "+ biblioH.getListeCopieDoc().get(docu.getEAN()));
													
											biblioH.emprunter(bibli, docu); 
											System.out.println("Après: nombre de copie " + bibli.getName()+ ": "+
											bibli.getListeCopieDoc().get(docu.getEAN()) + " nombre de copie " + biblioH.getName()
											+" : "+ biblioH.getListeCopieDoc().get(docu.getEAN()) + "\n emprunt reussi");
											
										}
									} catch (quotaException | nonDispoException | inscriptionException e) {
										e.printStackTrace();
									}
								}else {
									Livre livre = null;
									try {
										livre = bibli.searchISBN(str);
									} catch (formatISBNException e) {
										e.printStackTrace();
									}
									if(livre!=null) {
										try {
											if(typeAction == 0) {
											user.emprunter(bibli, livre);
											System.out.println("emprunt reussi \n" + user.toString());
											
											}
											else {
												biblioH.emprunter(bibli, docu);
												System.out.println("emprunt reussi");
												
											}
										} catch (quotaException | nonDispoException | inscriptionException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}else {
										System.out.println("ce document n'as pas été trouver");
									}
								}
							}else {
								System.out.println("cette bibliotheque n'est pas valide");
							}
					
				
					System.out.println("Entrer pour revenir au menu");
					str = sc.nextLine();
					commande=0;
				}
				}
			}
			//si commande 11: rendre un document 
			else if(commande ==11) {
				System.out.println("Entrer 0 pour faire remettre un utilisateur ou 1 pour faire remettre une bibliotheque \n"
						+ "Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1 \n Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1 \n Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					Utilisateur user = null; 
					Bibliotheque biblioH = null; 
					if(typeAction == 0 && commande != 0) {
						System.out.println("Entrer l'id de l'utilisateur :");
						str = sc.nextLine();
						try {
						Integer id = Integer.parseInt(str);
						if(reseau.getListeUtilisateur().containsKey(id)){
							user = reseau.getListeUtilisateur().get(id);
						}
						else {
							System.out.println("Cet utilisteur n'est pas enregistré dans le reseau \n Taper entrer pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Veuillez entrer un nombre pour l'identifiant \n Taper entrer pour revenir au menu");
							str = sc.nextLine();
							commande = 0;
						}
					}
					
					else if(typeAction == 1 && commande != 0) {
						System.out.println("Entrer le nom de la bibliotheque qui remet");
						str = sc.nextLine().toLowerCase();
						if(reseau.getListeBiblio().containsKey(str)) {
							biblioH = reseau.getListeBiblio().get(str);
						}
						else {
							System.out.println("Cette bibliothèque n'est pas dans le reseau \n Taper entrer pour revenir au menu");
							str = sc.nextLine();
							commande = 0;
							
						}
							
					}
					if(commande != 0) {
							System.out.println("Entrer le nom d'une bibliotheque dans laquelle remettre");
							str = sc.nextLine().toLowerCase();
							if(reseau.getListeBiblio().containsKey(str)) {
								Bibliotheque bibli = reseau.getListeBiblio().get(str);
								
								System.out.println("Entrer l'EAN ou l'ISBN du document que vous voulez remettre");
								str = sc.nextLine();
								Document docu = null;
								try {
									docu = reseau.searchEAN(str);
								} catch (formatEANException e) {
									e.printStackTrace();
								}
								if(docu!=null) {
									try {
										if(typeAction == 0) {
										user.remettre(bibli, docu);
										System.out.println("remise reussie");
										}
										else {
											biblioH.remettre(bibli, docu);
											System.out.println("remise reussie");
										}
									} catch (inscriptionException e) {
										e.printStackTrace();
									}
								}else {
									Livre livre = null;
									try {
										livre = reseau.searchISBN(str);
									} catch (formatISBNException e) {
										e.printStackTrace();
									}
									if(livre!=null) {
										try {
											if(typeAction == 0) {
											user.remettre(bibli, livre);
											System.out.println("remise reussie");
											} else {
												biblioH.remettre(bibli, livre);
												System.out.println("remise reussie");
											}
										} catch (inscriptionException e) {
											e.printStackTrace();
										}
									}else {
										System.out.println("ce document n'as pas été trouvé");
									}
								}
							}else {
								System.out.println("cette bibliotheque n'est pas valide");
							}
					System.out.println("Taper sur entree pour revenir au menu");
					str = sc.nextLine();
					commande=0;
				}
				}	
			}
			//si commande 12: inscrire un utilisateur 
			else if(commande ==12) {
				System.out.println("Entrée l'id de l'utilisateur à inscrire dans une bibliotheque:");
				System.out.println("Taper sur entreée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					Integer id = Integer.parseInt(str);
					if(reseau.getListeUtilisateur().containsKey(id)){
						Utilisateur user = reseau.getListeUtilisateur().get(id);
						System.out.println("Entrer le nom dans laquelle l'inscrire");
						str = sc.nextLine().toLowerCase();
						if(reseau.getListeBiblio().containsKey(str)) {
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							try {
								user.sInscrire(bibli);
								System.out.println(user.getName()+" a ete inscrit dans :"+ bibli.getName());
							} catch (inscriptionException e) {
								e.printStackTrace();
							}
							
						}else {
							System.out.println("cette bibliotheque n'est pas valide");
						}
					}else {
						System.out.println("cet identifiant ne correspond à aucun utilisateur du reseau");
					}
					System.out.println("Taper sur entree pour revenir au menu");
					str = sc.nextLine();	
					commande=0;
			
				}	
			}
			//si commande 13: afficher un utilisateur 
			else if(commande ==13) {
				System.out.println("Entrée l'id de l'utilisateur à afficher:");
				System.out.println("Taper sur entreée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
					Integer id = Integer.parseInt(str);
					if(reseau.getListeUtilisateur().containsKey(id)){
						Utilisateur user = reseau.getListeUtilisateur().get(id);
						System.out.println(user);
					}
					else {
						System.out.println("cet identifiant ne correspond à aucun utilisateur du reseau");
					}
					}catch(NumberFormatException e ) {
						System.out.println("Veuillez entrer un nombre pour l'identifiant");
					}
					
					System.out.println("Taper sur entree pour revenir au menu");
					str = sc.nextLine();
					commande=0;
				}
				
			
			}	
			
			else if(commande ==14) {
				reseau.afficherUtilisateur();
				System.out.println("Taper sur entree pour revenir au menu");
				String str = sc.nextLine();
				commande=0;
				
			}
			//si commande 15: quitter 
			else if(commande == 15) {
				marche=false;
				System.out.println("merci d'avoir utilisé le menu du réseau ! à bientot pour de nouvelles lectures !");
			}
			
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		
		Reseau parisBiblios = new Reseau(); 
		Bibliotheque aimeCesaire = new Bibliotheque("aime cesaire",parisBiblios ); 
		Bibliotheque edmondRostand = new Bibliotheque("edmond rostand",parisBiblios ); 
		Bibliotheque jeanPierreMelville = new Bibliotheque("jean pierre melville",parisBiblios ); 
		Bibliotheque oscardWilde = new Bibliotheque("oscar wilde",parisBiblios ); 
		Bibliotheque saintSimon = new Bibliotheque("saint simon",parisBiblios ); 
		parisBiblios.addBiblio(aimeCesaire);
		parisBiblios.addBiblio(edmondRostand);
		parisBiblios.addBiblio(jeanPierreMelville);
		parisBiblios.addBiblio(oscardWilde);
		parisBiblios.addBiblio(saintSimon);
		
		//On recupere les donnees  
		if(args.length > 0)
		{
			File tempFile = new File(args[0]);
			
			if(tempFile.exists())
			{
				System.out.println("[Main] Reading the file " + args[0] + " ...");
						
				//We start by reading the CSV file
				FileReader.getDataFromCSVFile(args[0], parisBiblios);
				
				System.out.println("[Main] End of the fi le " + args[0] + ".");
			}
			else
			{
				System.out.println("[Main] No file " + args[0]);
			}
		}
		else
		{
			System.out.println("[Main] You should enter the CSV file path as a parameter.");
		}
		
		Utilisateur user= new Utilisateur("Alya", 5);
		parisBiblios.getListeUtilisateur().put(user.getId(), user);
		
		Utilisateur user2= new Utilisateur("Clem", 5);
		parisBiblios.getListeUtilisateur().put(user2.getId(), user2);
		
		Utilisateur user3= new Utilisateur("Mme Brasier", 5);
		parisBiblios.getListeUtilisateur().put(user3.getId(), user3);
		
		Utilisateur user4= new Utilisateur("M Bouillaguet", 5);
		parisBiblios.getListeUtilisateur().put(user4.getId(), user4);
		
		menu(parisBiblios);
		
		

}
}
