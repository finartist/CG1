from bandit import Bandits
import banditstrategy as bs
import matplotlib.pyplot as plt
import numpy as np

# DONT CHANGE THE CONTENT OF THIS CLASS

# These are the "Tableau 20" colors as RGB.
tableau20 = [(31, 119, 180), (174, 199, 232),
             (255, 127, 14), (255, 187, 120),
             (44, 160, 44), (152, 223, 138),
             (214, 39, 40), (255, 152, 150),
             (148, 103, 189), (197, 176, 213),
             (188, 189, 34), (219, 219, 141),
             (227, 119, 194), (247, 182, 210),
             (140, 86, 75), (196, 156, 148),
             (127, 127, 127), (199, 199, 199),
             (23, 190, 207), (158, 218, 229)]


for i in range(len(tableau20)):
    r, g, b = tableau20[i]
    tableau20[i] = (r / 255., g / 255., b / 255.)


def plot_scenario(strategies, names, scenario_id=1):
    probabilities = get_scenario(scenario_id)

    plt.figure(figsize=(6, 4.5))

    ax = plt.subplot(111)
    ax.spines["top"].set_visible(False)
    ax.spines["bottom"].set_visible(False)
    ax.spines["right"].set_visible(False)
    ax.spines["left"].set_visible(False)

    ax.get_xaxis().tick_bottom()
    ax.get_yaxis().tick_left()

    plt.yticks(fontsize=14)
    plt.xticks(fontsize=14)
    plt.xlim((0, 1300))

    # Remove the tick marks; they are unnecessary with the tick lines we just plotted.
    plt.tick_params(axis="both", which="both", bottom="on", top="off",
                    labelbottom="on", left="off", right="off", labelleft="on")

    for rank, (strategy, name) in enumerate(zip(strategies, names)):
        plot_strategy(probabilities, strategy, name, rank)

    plt.title("Bandits: " + str(probabilities), fontweight='bold')
    plt.xlabel('Number of Trials', fontsize=14)
    plt.ylabel('Cumulative Regret', fontsize=14)
    plt.legend(names)
    plt.show()


def plot_strategy(probabilities, strategy, name, rank):
    strat_label = name
    bandits = Bandits(np.array(probabilities))
    strat = bs.BanditStrategy(bandits, strategy)
    strat.sample_bandits(1000)
    band_regret = regret(np.array(probabilities), strat.choices.astype(int))
    x = np.linspace(1, len(band_regret) + 1, len(band_regret))
    plt.plot(x, band_regret, label=strat_label, lw=2.5, color=tableau20[rank * 2])

    plt.xlim(0, 1000)


def regret(probabilities, choices):
    p_opt = np.max(probabilities)
    return np.cumsum(p_opt - probabilities[choices])


def get_scenario(scenario_id=1):
    if scenario_id == 1:
        return [0.05, 0.03, 0.06]
    elif scenario_id == 2:
        return [0.1, 0.1, 0.1, 0.1, 0.9]
    elif scenario_id == 3:
        return [0.1, 0.11, 0.09, 0.095, 0.12]
    elif scenario_id == 4:
        return [0.01, 0.02, 0.03, 0.04, 0.05]
    else:
        raise ValueError('Use a scenario id in range of [1,4]')
