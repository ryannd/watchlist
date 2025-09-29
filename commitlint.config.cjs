module.exports = {
  extends: ["@commitlint/config-conventional"],
  rules: {
    "header-match-team-pattern": [
      2,
      "always",
      /^([\u231A-\uFFFF]+) \((frontend|backend|root)\): .+$/
    ],
    "scope-enum": [2, "always", ["frontend", "backend", "root"]]
  }
};