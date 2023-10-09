#!/bin/sh

# Decrypt the file
#mkdir $HOME/iosApp/secrets
# --batch to prevent interactive command
# --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="$LARGE_SECRET_PASSPHRASE" \
--output $HOME/iosApp/Configuration/Config.xcconfig $HOME/iosApp/Configuration/Config.xcconfig.gpg