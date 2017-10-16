package src;

import java.util.ArrayList;
import java.util.List;

/**
 *  An object of type Deck represents a deck of playing cards.  The deck
 *  is a regular poker deck that contains 52 regular cards and that can
 *  also optionally include two Jokers.
 *  author Bryon Steinwand
 *  modified by John Randolph
 */
public class Deck 
{
   
   private List<Card> deck;
   private int cardsUsed;
   private final int cardsInFullDeck;

   public Deck(boolean includeJokers, int jokerValue, int faceCardValue, int aceValue) 
   {
	   deck = openNewDeck(includeJokers, jokerValue, faceCardValue, aceValue); 
	   if (includeJokers)
		   cardsInFullDeck = 54;
	   else
		   cardsInFullDeck = 52;
	   shuffle();
   }

   public Deck(int faceCardValue, int aceValue) 
   {
	   this(false, -1, faceCardValue, aceValue);   // Just call the other constructor in this class.
   }
   
   public Deck() 
   {
	   this(false, -1, 10, 1);   // Just call the other constructor in this class.
   }
   
   private void shuffle() 
   {
	   List<Card> tempDeck = new ArrayList<Card>();
	   while(deck.size()> 0)
	   {
		   int rand = (int)(Math.random()*(deck.size()));
		   tempDeck.add(deck.get(rand));	
		   //System.out.println(rand);
		   deck.remove(rand);
	   }
      cardsUsed = 0;
      deck = tempDeck;
   }
   
   /**
    * As cards are dealt from the deck, the number of cards left
    * decreases.  This function returns the number of cards that
    * are still left in the deck.  The return value would be
    * 52 or 54 (depending on whether the deck includes Jokers)
    * when the deck is first created or after the deck has been
    * shuffled.  It decreases by 1 each time the dealCard() method
    * is called.
    */
   public int cardsLeft() 
   {
      return cardsInFullDeck - cardsUsed;
   }
   
   /**
    * Removes the next card from the deck and return it.  It is illegal
    * to call this method if there are no more cards in the deck.  You can
    * check the number of cards remaining by calling the cardsLeft() function.
    * @return the card which is removed from the deck.
    * @throws IllegalStateException if there are no cards left in the deck
    */
   public Card dealCard() 
   {
      if (cardsUsed == cardsInFullDeck)
         return null;
      else
      {
    	  Card nextCard = deck.get(0);
    	  deck.remove(0);
    	  cardsUsed++;
    	  return nextCard;
      } 
   }
   
   /**
    * Test whether the deck contains Jokers.
    * @return true, if this is a 54-card deck containing two jokers, or false if
    * this is a 52 card deck that contains no jokers.
    */
   public boolean hasJokers() 
   {
      return (cardsInFullDeck == 54);
   }
   
   private List<Card> openNewDeck(boolean hasJokers, int jokerValue, int faceCardValue, int aceValue) 
	{
	    List<Card> myDeck = new ArrayList<Card>();
	   
	    myDeck.add(new Card2("Two", "Hearts", 2));
		myDeck.add(new Card2("Three", "Hearts", 3));
		myDeck.add(new Card2("Four", "Hearts", 4));
		myDeck.add(new Card2("Five", "Hearts", 5));
		myDeck.add(new Card2("Six", "Hearts", 6));
		myDeck.add(new Card2("Seven", "Hearts", 7));
		myDeck.add(new Card2("Eight", "Hearts", 8));
		myDeck.add(new Card2("Nine", "Hearts", 9));
		myDeck.add(new Card2("Ten", "Hearts", 10));
		myDeck.add(new Card2("Jack", "Hearts", faceCardValue));
		myDeck.add(new Card2("Queen", "Hearts", faceCardValue));
		myDeck.add(new Card2("King", "Hearts", faceCardValue));

		myDeck.add(new Card2("Two",  "Spades", 2));
		myDeck.add(new Card2("Three",  "Spades", 3));
		myDeck.add(new Card2("Four",  "Spades", 4));
		myDeck.add(new Card2("Five",  "Spades", 5));
		myDeck.add(new Card2("Six",  "Spades", 6));
		myDeck.add(new Card2("Seven",  "Spades", 7));
		myDeck.add(new Card2("Eight",  "Spades", 8));
		myDeck.add(new Card2("Nine",  "Spades", 9));
		myDeck.add(new Card2("Ten",  "Spades", 10));
		myDeck.add(new Card2("Jack",  "Spades", faceCardValue));
		myDeck.add(new Card2("Queen",  "Spades", faceCardValue));
		myDeck.add(new Card2("King",  "Spades", faceCardValue));

		myDeck.add(new Card2("Two",  "Diamonds", 2));
		myDeck.add(new Card2("Three",  "Diamonds", 3));
		myDeck.add(new Card2("Four",  "Diamonds", 4));
		myDeck.add(new Card2("Five",  "Diamonds", 5));
		myDeck.add(new Card2("Six",  "Diamonds", 6));
		myDeck.add(new Card2("Seven",  "Diamonds", 7));
		myDeck.add(new Card2("Eight",  "Diamonds", 8));
		myDeck.add(new Card2("Nine",  "Diamonds", 9));
		myDeck.add(new Card2("Ten",  "Diamonds", 10));
		myDeck.add(new Card2("Jack",  "Diamonds", faceCardValue));
		myDeck.add(new Card2("Queen",  "Diamonds", faceCardValue));
		myDeck.add(new Card2("King",  "Diamonds", faceCardValue));

		myDeck.add(new Card2("Two",  "Clubs", 2));
		myDeck.add(new Card2("Three",  "Clubs", 3));
		myDeck.add(new Card2("Four",  "Clubs", 4));
		myDeck.add(new Card2("Five",  "Clubs", 5));
		myDeck.add(new Card2("Six",  "Clubs", 6));
		myDeck.add(new Card2("Seven",  "Clubs", 7));
		myDeck.add(new Card2("Eight",  "Clubs", 8));
		myDeck.add(new Card2("Nine",  "Clubs", 9));
		myDeck.add(new Card2("Ten",  "Clubs", 10));
		myDeck.add(new Card2("Jack",  "Clubs", faceCardValue));
		myDeck.add(new Card2("Queen",  "Clubs", faceCardValue));
		myDeck.add(new Card2("King",  "Clubs", faceCardValue));

		myDeck.add(new Card("Ace", "Hearts", aceValue));
		myDeck.add(new Card("Ace", "Spades", aceValue));
		myDeck.add(new Card("Ace", "Diamonds", aceValue));
		myDeck.add(new Card("Ace", "Clubs", aceValue));
		
		if(hasJokers)
		{
			myDeck.add(new Card("Joker", "#1", jokerValue));
			myDeck.add(new Card("Joker", "#2", jokerValue));
		}
		return myDeck;
	}
}