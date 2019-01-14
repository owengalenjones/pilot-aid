#!/bin/bash

echo $( pwd )

if [ -f "project.clj" ]; then
  echo "Installing lein deps..."
  lein deps
fi

if [ -f "package.json" ]; then
  echo "Installing yarn modules..."
  yarn install
fi

if [ -f "src/js/index.js" ]; then
  echo "Building webpack..."
  yarn webpack
fi
