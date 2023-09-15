/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'background': '#e4e4e7',
        'primary': '#4ade80',
        'button': '#0c0a09',       
      },
      fontFamily: {
        'roboto': ['Roboto', 'sans-serif'],
      },
    },
    plugins: [],
  }
}