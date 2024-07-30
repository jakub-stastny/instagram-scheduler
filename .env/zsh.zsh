save-function-list

load ~/.zsh/environments/basic.zsh

alias emacs="emacs -nw --load $PWD/.env/emacs.el"

if test -f .env/tokens.sh; then
  source ".env/tokens.sh"
else
  echo "If you just cloned this repo, make sure to export the necessary tokens as ENV variables in .env/tokens.sh"
fi

report-custom-functions
