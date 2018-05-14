#needed to go to File->Default Settings->Interpreter and change it to Python 3.6, the install gym there, go back to None
#run this file (choose interpreter there)
#see https://stackoverflow.com/questions/31235376/pycharm-doesnt-recognise-installed-module?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
import gym
import numpy as np

def getCartPosBin(val):
    if(val < -1.25):
        return 0
    elif(val >= -1.25  and val < 0):
        return 1
    elif(val >= 0 and val < 1.25):
        return 2
    else:
        return 3

def getCartVelocityBin(val):
    if (val < -5):
        return 0
    elif (val >= -5 and val < 0):
        return 1
    elif (val >= 0 and val < 5):
        return 2
    else:
        return 3

def getPoleAngleBin(val):
    if (val < -8):
        return 0
    elif (val >= -8 and val < -4):
        return 1
    elif (val >= -4 and val < 0):
        return 2
    elif (val >= 0 and val < 4):
        return 3
    elif (val >= 4 and val < 8):
        return 4
    else:
        return 5

def getPoleAngleDiffBin(val):
    if(val < 0):
        return 0
    else:
        return 1

def updateValueMatrix(matrix, episodeObservations, returns, numEpisode, alpha):
    numVisitsMatrix = np.zeros((4, 4, 6, 2))
    i = 0
    if(numEpisode == 0):
        alpha = 1

    for state in episodeObservations:
        index = (getCartPosBin(state[0]), getCartVelocityBin(state[1]), getPoleAngleBin(state[2]), getPoleAngleDiffBin(state[3]))
        numVisitsMatrix[index[0], index[1], index[2], index[3]] += 1
        matrix[index[0], index[1], index[2], index[3]] += alpha/numVisitsMatrix[index[0], index[1], index[2], index[3]] *\
                                                          (returns[i] - matrix[index[0], index[1], index[2], index[3]])
        i += 1
    return

def printNonZeroIndices(matrix):
    cartPos, cartVel, poleAng, poleAngDiff = np.nonzero(matrix)
    for i in range(len(cartPos)):
        print("%i, %i, %i, %i: %f" % (cartPos[i], cartVel[i], poleAng[i], poleAngDiff[i],
                                      matrix[cartPos[i], cartVel[i], poleAng[i], poleAngDiff[i]]))

env = gym.make('CartPole-v0')

maxTimesteps = 100
valueMatrix = np.zeros((4, 4, 6, 2))

for i_episode in range(20):
    print ("episode %i" % i_episode)
    observation = env.reset()
    observations = []
    episodeReturns = []
    for t in range(maxTimesteps):
        env.render()
        #print(observation)
        action = env.action_space.sample()
        observation, reward, done, info = env.step(action)

        observations.append(observation)
        episodeReturns.insert(0, reward)
        if(t is not 0):
            episodeReturns[0] += episodeReturns[1]

        if done:
            print("Episode finished after {} timesteps".format(t+1))
            break

    updateValueMatrix(valueMatrix, observations, episodeReturns, i_episode, 0.5)
    print(printNonZeroIndices(valueMatrix))

env.close()