module.exports = {
  extends: ["plugin:@next/next/recommended", "turbo", "prettier"],
  rules: {
    "@next/next/no-html-link-for-pages": "off",
    "react/jsx-key": "off",
  },
};
