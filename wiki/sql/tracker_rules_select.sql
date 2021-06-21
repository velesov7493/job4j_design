SELECT r.id_role, r.id_rule, roles.roName, rules.ruName
FROM tr_roles_rules AS r
    INNER JOIN tz_roles AS roles ON r.id_role=roles.id
    INNER JOIN tz_rules AS rules ON r.id_rule=rules.id;

SELECT r.id_role, r.id_rule, roles.roName, rules.ruName
FROM tr_roles_rules AS r
    INNER JOIN tz_roles AS roles ON r.id_role=roles.id AND r.id_role=1
    INNER JOIN tz_rules AS rules ON r.id_rule=rules.id;

SELECT r.id_role, r.id_rule, roles.roName, rules.ruName
FROM tr_roles_rules AS r
    INNER JOIN tz_roles AS roles ON r.id_role=roles.id
    INNER JOIN tz_rules AS rules ON r.id_rule=rules.id AND r.id_rule BETWEEN 5 AND 8
ORDER BY 1,2;