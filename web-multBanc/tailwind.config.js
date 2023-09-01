/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'background': '#F2F1E6',
      },
      fontFamily: {
        'roboto': ['Roboto', 'sans-serif'],
      },
    },
    plugins: [],
  }
}