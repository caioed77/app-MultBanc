/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'background': '#d4d4d8',
        'primary': '#2F1C6A',
        'button': '#22c55e',       
      },
      fontFamily: {
        'roboto': ['Roboto', 'sans-serif'],
      },
    },
    plugins: [],
  }
}