#!/bin/bash

# This script will publish the aggregated javadocs found in the project's "target" directory.
# The javadocs are committed and pushed to the git repository's gh-pages branch.
# Be sure to customize this file to reflect your SDK project's settings (git url,

# Publish javadocs only for a tagged-release.
if [[ "${GITHUB_REF_TYPE}" == "tag" && -n "${GITHUB_REF_NAME}" ]]; then

    printf "\n>>>>> Publishing javadoc for release build: repo=%s release=%s run_num=%s job=%s\n" ${GITHUB_REPOSITORY} ${GITHUB_REF_NAME} ${GITHUB_RUN_NUMBER} ${GITHUB_JOB}

    printf "\n>>>>> Cloning repository's gh-pages branch into directory 'gh-pages'\n"
    rm -fr ./gh-pages
    git clone --branch=gh-pages https://${GH_TOKEN}@github.com/IBM/eventstreams-java-sdk.git gh-pages

    printf "\n>>>>> Finished cloning...\n"

    pushd gh-pages

    # Create a new directory for this branch/tag and copy the javadocs there.
    printf "\n>>>>> Copying javadocs to new directory: docs/%s\n" ${GITHUB_REF_NAME}
    rm -rf docs/${GITHUB_REF_NAME}
    mkdir -p docs/${GITHUB_REF_NAME}
    cp -rf ../target/site/apidocs/* docs/${GITHUB_REF_NAME}

    printf "\n>>>>> Generating gh-pages index.html...\n"
    ../build/generateJavadocIndex.sh > index.html

    printf "\n>>>>> Committing new javadoc...\n"
    git add -f .
    git commit -m "docs: latest javadoc for ${GITHUB_REF_NAME} (${GITHUB_SHA})"
    git push -f origin gh-pages

    popd

    printf "\n>>>>> Published javadoc for release build: repo=%s release=%s run_num=%s job=%s\n" ${GITHUB_REPOSITORY} ${GITHUB_REF_NAME} ${GITHUB_RUN_NUMBER} ${GITHUB_JOB}

else

    printf "\n>>>>> Javadoc publishing bypassed for non-release build: repo=%s branch=%s run_num=%s job=%s\n" ${GITHUB_REPOSITORY} ${GITHUB_REF_NAME} ${GITHUB_RUN_NUMBER} ${GITHUB_JOB}

fi
