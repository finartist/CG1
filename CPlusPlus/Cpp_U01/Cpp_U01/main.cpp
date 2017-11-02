#include <iostream>
#include <string>
#include <vector>
#include <windows.h>

// the text of the actual textadventure. world[row][column][eventtext]
// row == row of the world
// column == column of the world
// eventtext == 0 -> first time you enter location
//           == 1 -> every other time you enter location
//           == 2 -> triggered event
const std::string world[3][3][3] = { \
{ {"WOOD\nYou get into the wood. It is dark and cool. You feel very at home here.\n\n",

"WOOD\nYou don't know where you are. Everything looks the same... (Have fun getting out of here. *hehehe*)\n"
"POSSIBLE EXITS: ???\n\n",

"Oh, come on! Use your fucking phone! You've got an app for that kind of situation! Geeezzz, we do not have 1337.\n"
"USE 'use phone' to get out of the wood.\n\n"},

{ "LAKE\nYou stand at the lake's edge. It's a pretty big and deep lake. I wouldn't recommend swimming in it\n"
"but you won't do it anyway, right?\n\n",

"LAKE\nThe lake is like a mirror. You cannot do anything here, but go east for the witch's home, west for the wood\n"
"or south for the castle.\n"
"POSSIBLE EXITS : e, w, s\n\n",

"LAKE\nYou try to swim a little but a water monster graps your ankle and pulls you down. After that you two play\n"
"some Super Smash Bros. and the monster eats you alive. It was a funny evening. At least for me.\n"
"*******THE END********" },

{ "THE WITCH'S HOME\nYou arrive at the witch's home. Not really a typical housing for a witch. Well, it seems\n"
"it's not just a housing for ONE witch. Seems more like a company... W.I.T.C.H Inc it says on the sign of the 60\n"
"stories building. You enter through the big door. The receptionist greets you warmly. 'Hello, I guess you want\n"
"to make use of our services? The next free employee will be there for you in about: 666 days.' You ignore\n"
"the receptionist and go straigth to the next office. You open the door. 'W-w-w-what is happening here?!' It seems\n"
"you surprised a witch. She frantically tries to look busy but you figured out that she had nothing to do and was\n"
"just playing paper toss. You get right to the point and tell her, if she mixes that tincture for you, you won't tell\n"
"anyone of what you saw some seconds before. 'O-o-oK. But you need to get me some ingredients.' You want to ask\n"
"why she couldn't do it but then you remember the RPG-rules, so you agree to get a leaf from THE BIG OAK, a fish\n"
"from the SHORE and a geolausau from a CAVE.\n\n",

"THE WITCH'S HOME\nYou stand in the office of the witch. It's pretty messy but it doesn't look like the mess derives\n"
"from work. 'Do you have a leaf from THE BIG OAK, a fish from the SHORE and a geolausau from a CAVE?'\n"
"There is the lake to the west and the shore to the south.\n"
"POSSIBLE EXITS : w, s\n\n",

"THE WITCH'S HOME\n'Good work!' *bubble* *squeeeeezz* *steam* 'Here you have your tincture.' You assure her you won't tell anyone\n"
"about her work time activities and leave.\n\n"
} },


{ { "CLEARING\nA great clearing in the woods. There is nothing of interest.\n...\n....\n.....\n"
"There is a dragon but nothing else of your particular interest.\n\n",

"CLEARING\nThere is still a dragon, but you cannot do anything here. You can go north into the wood,\n"
"south to the big oak tree or east to the castle.\n"
"POSSIBLE EXITS : n, e, s\n\n",

"CLEARING\nYou attack the dragon and it burns you to ashes. I knew you are an accident with legs. Stupid you.\n"
"******THE END******" },

{ "GREAT HALL\nYou stand next to a column near a great throne. Your heart is flattering\n"
"just like the first time you entered this room. Suddenly, the door was slammed open and\n"
"a muscular figure appeared in the bright sunlight. You try to catch a good view of it and\n"
"accidentially stumble over something important lying around. The figure arrives in front of\n"
"the throne ' I am the hero you required. I traveled 3 weeks to get here, my king, and to vanquish\n"
"the evil -' *RUMBLE* *BOOOOOM* *CRASH* ............\n"
"Smoke clears away... The so-called hero lies on the ground. Red liquid is splattered around him and\n"
"a spotligth is where his head should be. You look down to your feet and discover that you triped up on\n"
"some cable. You cannot see any connection to what happened. King Salzorbygotrelstrum (Salzor for short)\n"
"looks at you. You actually feel yourself shrinking right now. 'Oi, you there.', he says pointing\n"
"in your direction, 'You are the hero now. Go and do something heroic.' WOW, WOW, wait... WHAT?! You,\n"
"an accident with legs, shall go on an adventure?! Oh my, I better get my helmet.\n"
"You stand inside the castle's door. The sun is still shining, but you would prefer night time. Sun\n"
"usually hurts your eyes and your white skin dazzle other people. 'Is a hero nearby?' reaches your ear\n"
"from the south.\n\n",

"CASTLE\nYou are outside the castle. You can go south to the town's market, north to the lake, west to a clearing\n"
"in the woods or east to the shore. You can see nothing of interest.\n"
"POSSIBLE EXITS: n, e, s, w\n\n",

"CASTLE\n'Greetings hero #42. You performed heroic actions?' You tell King Salzor about the man and your quest.\n"
"'That... that was your feat? Couldn't he go for himself?' You explain the role of a hero in a RPG to the king.\n"
"'Well, then I must give you credit for this. *mumbles unwillingly* Here, have 1 Gold. Go and buy yourself some ice cream.'\n"
"You take the piece of gold and go home. Being a hero is hard business. You decide to lay down the title and just be a hero for fun\n\n"
"********THE END********" },

{ "SHORE\nYou follow the slight breeze to the shore. A lot of people are enjoying the sun and the water. It is the\n"
"first time you are here. Maybe because you hate both, sun and people. However, you need to get some fish. Unfortunately\n"
"the water is to flat to catch fish here. I guess for you it isn't unfortunate because this way you have no need for\n"
"a fishing pole... or going into the water.\n\n",

"SHORE\nYou see a lot of jelly fish lying around together with joyful people. Maybe this will serve as 'fish' as well.\n"
"You can go north, to the witch's home, south to the cave or west to the castle.\n"
"POSSIBLE EXITS: n, w, s\n\n",

"SHORE\nYou pick up a jelly fish. It feels slimy but not bad at all...\n\n" } },


{ { "THE BIG OAK\nYou arrive at the oak tree. There is not much left of the tree because it is buried under leaves.\n"
"It is surprising that neither wind nor natural decomposition reduced the number of leaves. Seems like this tree is special.\n\n",

"THE BIG OAK\nThe tree is still barly visible. Leaves, leaves, leaves everywhere. They seem to be piled up deliberately.\n"
"From here you can go north to a clearing in the woods or east to the market.\n"
"POSSIBLE EXITS: n, e\n\n",

"THE BIG OAK\nYou take a leaf from the bottom of the pile. Suddenly, you hear a rustling. A tiny leaf lands on your\n"
"forehead, followed by the rest of the heap. After you've complained a lot about your life and dug your way out, you\n"
"stand in front of the tree. Your trust in trees decrease heavily.\n\n" },

{ "MARKET\nYou entered the market. 'Is a hero nearby?' You hear the voice again. It's shrill, lound and\n"
"annoying but you still decide to look out for it. A huge man nearly knocks you down while you try to hop from\n"
"one shadow to another. Oh wait, whom am I talking about. Silly me. Of course you are slammed to the floor by that particular man.\n"
"It's actually a miricale how you manage to stay unhurt.'What the heck is wrong with you?' That was THAT voice!\n"
"You respond that you are the hero and want to help. As an aside, you point out his voice. The man starts to\n"
"cry. 'Please go to the witch and get me some voice changing tincture. This voice is ruining my life!' You ask\n"
"why he couldn't go by himself. 'This is a RPG right? The hero does this kind of stuff. No need to go myself.'\n"
"You agree to the request although you hoped for a more heroic quest.\n\n",

"MARKET\nYou stand in the middle of the marketplace. It's crowded and you feel uncomfortable. You don't like people.\n"
"That is why you never leave your home, except for big events. You can go north to the castle, east to a cave or west to the big oak.\n"
"POSSIBLE EXITS: n, e, w\n\n",

"MARKET\nYou give the tincture to the man. 'Thank you! Very very much! I have to admit, I didn't think you could do it\n"
"but you did!' He pads your shoulder and you sink down on your knees. His hand is heavier than you expected. I would say\n"
"you are just as weak as a twig in a tornado but what do I know! Now you can go to the king and tell him about your heroic actions!\n\n" },

{ "CAVE\nYou arrive at a cave. It's dark and you hear a roar from the inside. It definitely means no good but you go in there anyway.\n"
"Sometimes I think you try to die on purpose. You go further and further inside. After you hit your head on the third stalactite you\n"
"decide to slowly go back to where you came from. As you arrive where you think the entrance was, you are surprised. It's not there.\n"
"You feel the face of the cave. It's not as cold as you expected. It's more... fluffy and soft. You think it is unusual for a wall\n"
"to be fluffy. The more you touch it the more sure you become it is no wall at all. Whatever it is, it steps aside and ignores you.\n\n",

"CAVE\nYou are now standing outide the cave, wondering what that thing inside was. From here you can get to the shore in the north or\n"
"or the market in the west.\n"
"POSSIBLE EXITS: n, w\n\n",

"CAVE\nYou remember the thing inside might be a geolausau. So you decide to go inside once more. Soon you find the thing again. You ask it\n"
"kindly if it would accompany you to the witch. 'Nah, I'd rather stay here.' You nod slowly and come up with idea to take some of it's\n"
"fur with you. It agrees and you leave the cave with some fur.\n\n" } }
};

// bool array for events. First two indices are row and column like in the text array.
// The third index[0] == true -> you visit the location at least a second time. Third index[1] == true -> event was triggered here
bool trigger[3][3][2] = { { { false, false },{ false, false },{ false, false } },{ { false, false },{ false, false },{ false, false } },\
{ {false, false},{ false, false },{ false, false }} };

// for the main game loop
bool isRunning = true;

// for the get_lost sequence in the woods
int walkCount = 0;

// tells the compiler these functions exist
void move(int, int, int[]);
void print(int[]);
void computeInput(std::string, int[]);

int main()
{
	// player position; is altered by move
	int pos[2] = { 1, 1 };
	std::string input;

	while (isRunning) {
		print(pos);
		// reads player input from console (not only std::cin << because it does not read whitespaces)
		getline(std::cin, input);
		computeInput(input, pos);
	}

	return 0;
}

// handles input from user depending on position
void computeInput(std::string input, int pos[]) {
	// last trigger event, the player wins, ends game
	if (trigger[2][1][1]) {
		std::cout << world[1][1][2];
		std::cout << "\n";
		system("pause");
		isRunning = false;
	}
	else if (input == "") {}
	// go north
	else if (input == "n") {
		move(-1, 0, pos);
	}
	//go east
	else if (input == "e") {
		move(0, 1, pos);
	}
	//go south
	else if (input == "s") {
		move(1, 0, pos);
	}
	//go west
	else if (input == "w") {
		move(0, -1, pos);
	}
	//pick up something if you are able to
	else if (input == "pick up") {
		// pick up fish
		if (pos[0] == 1 && pos[1] == 2 && !trigger[1][2][1]) {
			std::cout << world[1][2][2];
			// fish event was triggered
			trigger[1][2][1] = true;
		}
		// pick up leaf
		else if (pos[0] == 2 && pos[1] == 0 && !trigger[2][0][1]) {
			std::cout << world[2][0][2];
			// leaf event was triggerd
			trigger[2][0][1] = true;
		}
		// pick up geolausau
		else if (pos[0] == 2 && pos[1] == 2 && !trigger[2][2][1]) {
			std::cout << world[2][2][2];
			// geolausau event was triggered
			trigger[2][2][1] = true;
		}
		// if you cannot pick up something in that location (yet/anymore) 
		else {
			std::cout << "Whatever you try to pick up here, you definitly won't need it.\n"
				"Therefore I won't let you take it. Infinite inventories are a lie!\n\n";
		}
	}
	else if (input == "give") {
		// give fish, leaf and geolausau to witch if player hasn't done yet
		if (pos[0] == 0 && pos[1] == 2 && trigger[2][2][1] && trigger[2][0][1] && trigger[1][2][1] && !trigger[0][2][1]) {
			std::cout << world[0][2][2];
			trigger[0][2][1] = true;
		}
		// give tincture to man if if player hasn't done yet
		else if (pos[0] == 2 && pos[1] == 1 && trigger[0][2][1] && !trigger[2][1][1]) {
			std::cout << world[2][1][2];
			trigger[2][1][1] = true;
		}
		// player cannot give anything away (yet/anymore)
		else {
			std::cout << "You have nothing of any importance to give it to anyone around you.\n\n";
		}
	}
	// input to escape the wood
	else if (input == "use phone" && pos[0] == 0 && pos[1] == 0) {
		pos[0] = 0;
		pos[1] = 1;
	}
	// input on the lake -> ends game because player dies
	else if (input == "swim" && pos[0] == 0 && pos[1] == 1) {
		std::cout << world[0][1][2];
		system("pause");
		std::cout << "\n";
		isRunning = false;
	}
	// input on the clearing -> ends game because player dies
	else if (input == "attack" && pos[0] == 1 && pos[1] == 0) {
		std::cout << world[1][0][2];
		system("pause");
		std::cout << "\n";
		isRunning = false;
	}
	// no input is matching any case
	else {
		std::cout << "I do not know what you want to do, but it does not work.\n\n";
	}
}

void move(int x, int y, int pos[]) {
	// if in the woods
	if (pos[0] == 0 && pos[1] == 0) {
		// after 4 tries, the game tells the player what to do to escape
		if (trigger[0][0][1] && walkCount == 4) {
			std::cout << world[0][0][2];
		}
		// wood event is triggerd
		else {
			trigger[0][0][1] = true;
			walkCount += 1;
		}
	}
	// move north, east, west or south
	else {
		if ((x == 1 && pos[0] < 2) || (x == -1 && pos[0]>0)) {
			pos[0] += x;
		}
		if ((y == 1 && pos[1] < 2) || (y == -1 && pos[1]>0)) {
			pos[1] += y;
		}
	}
}

void print(int pos[]) {
	// prints the text, if you first enter a location
	if (!trigger[pos[0]][pos[1]][0]) {
		for (size_t i = 0; world[pos[0]][pos[1]][0][i] != '\0'; i++) {
			std::cout << world[pos[0]][pos[1]][0][i];
			Sleep(30);
		}
		for (size_t i = 0; world[pos[0]][pos[1]][1][i] != '\0'; i++)
		{
			std::cout << world[pos[0]][pos[1]][1][i];
			Sleep(30);
		}
		trigger[pos[0]][pos[1]][0] = true;
	}
	// prints the text after you already entered that location
	else {
		for (size_t i = 0; world[pos[0]][pos[1]][1][i] != '\0'; i++) {
			std::cout << world[pos[0]][pos[1]][1][i];
			Sleep(30);
		}
	}
}