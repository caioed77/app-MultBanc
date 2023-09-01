export default function SearchBar() {
  return (
    <div className="p-10">
      <input
        type="text"
        placeholder="Pesquisar..."
        className="w-[620px] px-4 py-2 rounded border border-gray-300 focus:outline-none focus:border-blue-500"
      />
    </div>
  );
}
