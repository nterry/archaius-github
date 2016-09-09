#!/usr/bin/env bash

# Exit on failure
set -e

# This assumes that the 2 following variables are defined:
# - SONAR_HOST_URL => should point to the public URL of the SQ server (e.g. for Nemo: https://nemo.sonarqube.org)
# - SONAR_TOKEN    => token of a user who has the "Execute Analysis" permission on the SQ server

# And run the analysis
# It assumes that the project uses Maven and has a POM at the root of the repo
if [ ${TRAVIS_BRANCH} == "master" ] && [ ${TRAVIS_PULL_REQUEST} == "false" ]; then
	# => This will run a full analysis of the project and push results to the SonarQube server.
	#
	# Analysis is done only on master so that build of branches don't push analyses to the same project and therefore "pollute" the results
	echo "Starting analysis by SonarQube..."
	mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -B -e -V \
		-Dsonar.host.url=${SONAR_HOST_URL} \
		-Dsonar.login=${SONAR_TOKEN}
fi