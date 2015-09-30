0. Author Information
---------------------

CS Username: bxw4574        Name: Ben Woodworth

1. Problem Analysis
---------

Summarize the analysis work you did. 
What new information did it reveal?  How did this help you?
How much time did you spend on problem analysis?

	To figure out how I was going to approach the problem,
	I read over the project description a couple times before
	going to sleep, and slept on the problem. When I started
	working on it the next day, I had a good idea of what I
	wanted the project to be like, and how I'd approach it.

2. Design
---------

Explain the design you developed to use and why. What are the major 
components? What connects to what? How much time did you spend on design?
Make sure to clearly show how your design uses the MVC model by
separating the UI "view" code from the back-end game "model".

	I developed a basic window that has a JTextArea for game
	output, JLists accompanied by buttons that display lists
	of items and halls, and a JProgressBar that displayed the
	player's current Life Force, and displays the experience
	as text. The model and the view both connect to the
	controller, but don't connect to each other. I have an
	observer pattern that lets the view keep its information
	up-to-date.

3. Implementation and Testing
-------------------

Describe your implementation efforts here; this is the coding and testing.

What did you put into code first?
	I started by making the view, because that's what the game
	was going to be based around.

How did you test it?
	I tested the game by playing it. I made sure there weren't
	any errors and that the game worked as the project description
	said it should.

How well does the solution work?
	I think the solution works very well. It's a simplistic UI that
	does everything it's asked of.

Does it completely solve the problem?
	I think my solution completely solves the problem.

Is anything missing?
	Everything in the project description was implemented in the game.

How could you do it differently?
	I'm not sure.

How could you improve it?
	Instead of having a text output, I could have a graphical
	representation of the room, and have animations of the player
	leaving and entering different hallways, fighting the monster,
	running into traps, etc.

How much total time would you estimate you worked on the project? 
	I spent an afternoon working on the code itself.

If you had to do this again, what would you do differently?
	I don't think I would do much differently.

4. Development Process
-----------------------------

Describe your development process here; this is the overall process.

How much problem analysis did you do before your initial coding effort?
	I read over the project description a few times the night before
	I wanted to start working on the project, and slept on it. I mostly
	had an idea of what the whole project was going to be like, but I
	looked back at the project description occasionally for clarifications.
	
How much design work and thought did you do before your initial coding effort?
	I didn't have to think too hard about what to do after writing most
	of the code. I did the project within a few hours, and checked the
	project description afterwards to make sure I had everything that was
	required. When I finished, I touched up the UI a bit to make it a
	bit prettier.

How many times did you have to go back to assess the problem?
	I didn't have to go back to assess the problem after I started writing
	the code. The only thing I had to do was occasionally check the
	project description to make sure I had everything I needed, and to
	make sure I was doing what the description wanted.

What did you learn about software development?
	I got some practice with MVC, which is something I'm not a custom
	to and haven't used much. I also haven't used an observer pattern
	in any of my code before this project.