# Proj4.java
Declare as a constant YOUR last name, another constant with YOUR 9-digit WID# and another constant
with the password CIS200$Fall23. (If your last name is < 4 letters, pad at the end with ‘z’)
- Username will consists of the first 4 letters contained in the constant NAME + last 4 digits of constant
WID. (Assume last name is at least 4 letters).Require the user to enter a username and password (only the password is case sensitive) before they are
allowed to play the game that follows. If the username AND/OR the password is/are invalid, display an
error message and ask the user to re-enter (OK to make your error specific to the error or generic such as
“User and/or Password is/are invalid”).
If invalid 3 times, display another error message (“Invalid Username and/or Password entered 3 times –
Exiting”) and lock out the user from playing the game by exiting the program. Once the username and password have been validated, have the program “deal” a single hand containing
five random playing cards that are unique. Each card will have a suit (Spades, Clubs, Hearts, or Diamonds)
and a value (2-10, Jack, Queen, King, or Ace) display the BEST (highest) classification for your poker hand (1-9). If none of the hands 1-9 are drawn,
then simply display your highest card. (From low to high: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A).
After displaying the results of your hand, ask the user if they wish to ‘Play Again?’ If so, repeat and ‘deal’
another hand of 5 cards, and display the result. Assume the cards are reshuffled and dealt from a new deck.
In other words, duplicates from the previous hand could be dealt.
Continue until the user replies they no longer want to play. (Program should accept ‘Y’ or ‘y’ or ‘N’ or ‘n’
ONLY – so validate and error check user response to “Play Again?”)
