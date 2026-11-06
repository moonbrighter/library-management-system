---
name: check-later
description: Add an item to the user's "to-check-later" list (ideas or observations to revisit after the current fix), or show the list. Use when the user runs /check-later, or says "add to my list", "remember to check X later", "put that on my to-check list", or asks what's on the list.
---

# check-later

The list is stored in Claude's project memory at:

`C:\Users\ricky\.claude\projects\C--Users-ricky-dev-remote-projects-java-library-management-system\memory\to-check-later.md`

## Step 0: make sure the list exists (always do this first)

Try to read the list file. If it's missing, create it before doing anything else, using exactly this template:

```markdown
---
name: to-check-later
description: The user's personal list of ideas/observations to revisit after finishing the current fix; read when they ask "what's on my list" or finish a problem
metadata:
  type: project
---

The user is fixing the review issues themselves to learn (Claude does not write the fixes). This is their list of side-ideas to come back to.
Items are added via the `check-later` skill. Mark items done with `[x]` rather than deleting them unless the user asks.

## Items
```

Then make sure `MEMORY.md` in the same folder has this pointer line. Create `MEMORY.md` if it's missing, and append the line if it isn't there yet:

`- [To-check-later list](to-check-later.md) — the user's ideas to revisit after the current fix; append via the check-later skill`

If the file exists but has no `## Items` heading, add the heading at the end rather than recreating the file.

Tell the user in one line when a new list was created.

## Adding an item (default when args are given)

1. Run Step 0.
2. Write the item from the user's words (the args, or what they just said). Keep their idea as they stated it. Don't solve it, judge it, or expand it into a fix, because the user is learning by doing the work themselves.
3. Append it under `## Items` in this format:
   `- [ ] (YYYY-MM-DD, <what they were working on, if known>) <idea, naming the files/methods involved in backticks>`
4. Before adding, check for a near-duplicate. If one exists, tell the user rather than adding it twice.
5. Confirm in one line: what was added and how many items are open.

## Showing the list (no args, or "what's on my list")

Run Step 0, then print the open `[ ]` items, followed by the done `[x]` items. If the list is empty, say so.

## Marking done

When the user says an item is done or handled, change `[ ]` to `[x]`. Delete an item only if the user asks.
