package com.example.jokelibrary;

public class JokeLibrary {
        private  String[] jokes = {
                "Whats the best thing about Switzerland? I dont't know but the flag is a big plus.",
                "I invented a new word... Plagarism.",
                "A neutron walks into a bar and asks how much for a beer. The bartender replies, 'For you, no charge.'",
                "Hear about the new Restaurant called Karma? There is no menu, you get whatever you deserve.",
                "Why do we tell actors to break a leg? Because every play has a cast.",
                "One Tectonic plate bumped into another and said, 'Sorry, my fault.'",
                "Helvetica and Times New Roman walk into a bar... bartender yells, 'Hey, we dont serve your type.'",
                "What do you call a Scottish iPhone? An 'Aye'Phone.",
                "How did the solipsist break up with his girlfriend? It's not you, it's me.",
                "So Zeno walks halfway into a bar....",
                "What is the fastest way to determine the sex of a chromosome? Pull down its genes.",

        };
        public  String getJoke(){
            String joke;
            int min = 0, max = jokes.length-1, random;
            random = (int) Math.round(Math.random()*((max - min)+min));
            return jokes[random];
        }
    }


