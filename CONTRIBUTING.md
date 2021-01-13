Contribution Guidelines
==

These are the guidelines for contribution to QME source code.

## Code style

- Do not put opening braces on their own line.
- Put spaces between `if`, `for`, `while`, and the following `(` .
- Add Javadoc for every class, enum, function, and top-level variable.
  - For classes, put the author and release it was made before.
  - For functions, put all parameters, thrown exceptions, and the return type.
- Log significant things that happen.
- If class `B` extends class `A`, the name of `A` needs to begin with class `A` usually.
  - Like `ButtonNonClickable`, but of course `UICompoentButton` is redundant.
- Make top-level variables `private` and `final` where possible.
  - Simple things like `Tile.x` or `Coordinate.y` are exempt from this.
  
  
## Code review / merge

- Make a pull request. Describe in the comments all issues that this closes.
- Get 1-3 reviews from programmers, then notify Adam to get it merged.
