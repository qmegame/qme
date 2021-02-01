#!/usr/bin/env python3
import subprocess
import inquirer
from typing import Dict
from termcolor import colored

INFO = """The classpath is hard to manage with different systems.
Here is a script to help move an example classpath for both systems
into the root path and name it .classpath"""


def get_user_selected_classpath() -> Dict[str, str]:
    """Get the user inputed classpath type"""
    print(f"\n{colored(INFO, 'green')}\n")
    classpath_names = ["./nix_classpath", "./osx_classpath"]
    classpath_question = [
        inquirer.List(
            "classpath",
            choices=classpath_names,
            message=colored("What classpath would you like? ", "yellow"),
        )
    ]
    what_classpath = inquirer.prompt(classpath_question)
    return what_classpath


def move_classpath() -> bool:
    classpath = get_user_selected_classpath()["classpath"]
    success = subprocess.run(f"cp {classpath} ../.classpath", shell=True, check=True)
    if success:
        print(colored(f"Moved {classpath} to ../.classpath", "green"))
    else:
        print(colored(f"Couldn't move {classpath}", "red"))


if __name__ == "__main__":
    move_classpath()
