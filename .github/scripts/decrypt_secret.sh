#!/bin/sh

# Decrypt the file
#mkdir $HOME/iosApp/secrets
# --batch to prevent interactive command
# --yes to assume "yes" for questions

echo $HOME
echo $PWD

gpg --quiet --batch --yes --decrypt --passphrase="$LARGE_SECRET_PASSPHRASE" \
--output $Home/./././iosApp/Configuration/Config.xcconfig ../iosApp/Configuration/Config.xcconfig.gpg