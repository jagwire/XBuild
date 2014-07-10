echo "### FOR X" >> ~/.profile
echo "export X_HOME=$1" >> ~/.profile
echo "export X_LIB_PATH=$X_HOME/dist" >> ~/.profile
echo "export PATH='$PATH':'$X_HOME'" >> ~/.profile

source ~/.profile

echo $PATH
