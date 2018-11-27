#node
#To get this version, you can use the apt package manager. Refresh your local package index by typing:

    sudo apt update
#Install Node.js from the repositories:

    sudo apt install nodejs
#If the package in the repositories suits your needs, this is all you need to do to get set up with Node.js. In most cases, you'll also want to also install npm, the Node.js package manager. You can do this by typing:

    sudo apt install npm

#yarn :
#1. Configure the repository
    curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
    echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list

#2. Install Yarn

    sudo apt-get update && sudo apt-get install --no-install-recommends yarn
