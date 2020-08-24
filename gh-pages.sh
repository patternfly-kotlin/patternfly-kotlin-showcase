#!/bin/bash
# Script to build and push the showcase to branch 'gh-pages'
#
# Prerequisites
#   - Clean git status (no uncommitted changes in current branch)
#
# Parameters
#   1. No parameters
#
# What it does
#   1. Assemble showcase
#   2. Push showcase to branch 'gh-pages'



ROOT=$PWD
BRANCH=$(git symbolic-ref -q HEAD)
BRANCH=${BRANCH##refs/heads/}
BRANCH=${BRANCH:-HEAD}

function box()
{
  local s="$*"
  tput setaf 3
  echo
  echo
  echo
  echo " -${s//?/-}-
| ${s//?/ } |
| $(tput setaf 4)$s$(tput setaf 3) |
| ${s//?/ } |
 -${s//?/-}-"
  tput sgr 0
}



# Prerequisites
if ! git diff --no-ext-diff --quiet --exit-code; then
    echo "Cannot deploy to gh-pages. You have uncommitted changes in the current branch."
    exit 1
fi



# Build showcase
box "Build showcase"
./gradlew assemble



# Push showcase to gh-pages
rm -rf /tmp/patternfly-fritz2-showcase
cd /tmp/
git clone -b gh-pages --single-branch git@github.com:patternfly-kotlin/patternfly-fritz2-showcase.git
cd patternfly-fritz2-showcase

box "Push showcase to gh-pages"
rm -rf *
cp -R ${ROOT}/build/distributions/* .
date > .build
git add --all
git commit -am "Update showcase"
git push -f origin gh-pages
cd ${ROOT}



# Done
box "  <<--==  Showcase successfully build and published.  ==-->>  "
