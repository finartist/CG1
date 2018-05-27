import numpy as np
import random


class BanditStrategy(object):
    """
        Implements a online, learning strategy to solve
        the Multi-Armed Bandit problem.

        parameters:
            bandits: a Bandit class with .pull method
            choice_function: accepts a self argument (which gives access to all the variables),
                             and returns and int between 0 and n-1
        methods:
            sample_bandits(n): sample and train on n pulls.
        attributes:
            N: the cumulative number of samples
            choices: the historical choices as a (N,) array
            bb_score: the historical score as a (N,) array
    """

    def __init__(self, bandits, choice_function):
        """
            INPUT: Bandits, function

            Initializes the BanditStrategy given an instance of the Bandits class
            and a choice function.
        """
        self.bandits = bandits
        n_bandits = len(self.bandits)
        self.wins = np.zeros(n_bandits)
        self.trials = np.zeros(n_bandits)
        self.N = 0
        self.choices = []
        self.score = []
        self.choice_function = choice_function

    def sample_bandits(self, n=1):
        """
           INPUT: in
           OUTPUT: None
           Simulate n rounds of running the bandit machine.
        """
        score = np.zeros(n)
        choices = np.zeros(n)

        # seed the random number generators so you get the same results every
        # time.
        np.random.seed(101)
        random.seed(101)

        for k in range(n):
            # sample from the bandits's priors, and select the largest sample
            choice = self.choice_function(self)

            # sample the chosen bandit
            result = self.bandits.pull(choice)

            # update priors and score
            self.wins[choice] += result
            self.trials[choice] += 1
            score[k] = result
            self.N += 1
            choices[k] = choice

        self.score = np.r_[self.score, score]
        self.choices = np.r_[self.choices, choices]


def random_choice(bandit: BanditStrategy):
    """
        Pick a bandit uniformly at random. Return the index of the winning bandit.
    """
    return np.random.randint(0, len(bandit.wins))


def greedy(bandit: BanditStrategy):
    """
       Pick the bandit with the current best observed proportion of winning.
       Return the index of the winning bandit.
    """
    # make sure to play each bandit at least once
    if len(bandit.trials.nonzero()[0]) < len(bandit.bandits):
        return np.random.randint(0, len(bandit.bandits))
    return np.argmax(bandit.wins / (bandit.trials + 1))


def epsilon_greedy(bandit: BanditStrategy, epsilon=0.1):
    """
        Pick a bandit uniformly at random epsilon percent of the time.
        Otherwise pick the bandit with the best observed proportion of winning.
        Return the index of the winning bandit.
    """
    r = np.random.random()
    if r <= epsilon:
        return np.random.randint(0, len(bandit.wins))
    else:
        win_percent = bandit.wins / bandit.trials.astype(float)
        return win_percent.argmax(axis=0)


def soft_max(bandit: BanditStrategy, tau=0.001):
    """
        Pick an bandit according to the Boltzman Distribution.
        Return the index of the winning bandit.
    """
    win_percent = (bandit.wins + 1) / (bandit.trials.astype(float) + 1)
    soft_calc = np.exp(win_percent / tau)
    denom = sum(soft_calc)
    return (soft_calc / denom).argmax(axis=0)


def regret(probabilities, choices):
    """
        INPUT: array of floats (0 to 1), array of ints
        OUTPUT: array of floats
        Take an array of the true probabilities for each machine and an
        array of the indices of the machine played at each round.
        Return an array giving the total regret after each round.
    """
    p_opt = np.max(probabilities)
    return np.cumsum(p_opt - probabilities[choices])
