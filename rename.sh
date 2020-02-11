#!/bin/bash
# Recursively apply given name to template
$given=$1
$snake=
$kebab=
# Change text within files
grep -rl 'ZZZZ' . | xargs sed -i "s@ZZZZ@$kebab@g"
# Change filenames appropriately
find . -depth -iname 'ZZZZ' -exec rename -v 'ZZZZ' $snake {} +
