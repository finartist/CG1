from plotbandits import plot_scenario
from banditstrategy import BanditStrategy, greedy, soft_max, epsilon_greedy, random_choice
import numpy as np

def ucb1(bandit: BanditStrategy, c = 1):
    """
        Pick the bandit according to the UCB1 strategy.
        Return the index of the winning bandit.
    """
    
    expectedRet = bandit.wins / (bandit.trials + 1)
    exploration = c * np.sqrt(np.log(bandit.N)/bandit.trials)
    
    return np.argmax(expectedRet  + exploration)


def decaying_epsilon_greedy(bandit: BanditStrategy, epsilon=0.1):
    """
        Pick the bandit according to the Decaying Epsilon Greedy strategy.
        Return the index of the winning bandit.
    """
    
    epsilon = epsilon-bandit.N*0.001
    if(epsilon < 0):
        epsilon = 0
    
    return epsilon_greedy(bandit, epsilon)


if __name__ == "__main__":
    algorithm_names = ["Greedy", "Random", "$\epsilon$ Greedy", "Softmax", "decaying $\epsilon$ Greedy", "UCB1"]
    strategies = [greedy, random_choice,
                  epsilon_greedy, soft_max,
                  decaying_epsilon_greedy, ucb1]

    for i in [1, 2, 3, 4]:
        plot_scenario(strategies=strategies, names=algorithm_names, scenario_id=i)
