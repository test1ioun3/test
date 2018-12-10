function* WeaponGenerator() {
    yield "katana";
    yield "wakizashi";
    yield "kusarigama";
}

for (let weapon of WeaponGenerator()) {
    assert(weapon !== undefined, weapon);
}