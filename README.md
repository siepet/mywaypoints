# MyWaypoints

## Easily configurable plugin to manage multiple waypoints on the server!

Each waypoint is described by JSON files, e.g:
Mesa.json:
```
{
  "name": "Mesa",
  "description": "Entrance to Mesa biome!",
  "location": [
    "x: -346",
    "y: 62",
    "z: 1052"
  ]
}

```



## Commands
`/waypoint list` - lists all available waypoints

`/waypoint name` - moves user to given waypoint's location

`/waypoint add name` - adds new `name` waypoint with user's current location

`/waypoint edit name` - saves user's current location as new location for waypoint with given name

`/waypoint delete name` - deletes waypoint with given name



