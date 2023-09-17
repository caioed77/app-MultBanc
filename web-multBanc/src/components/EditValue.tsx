type TEditField = {
  displayname: string;

}

export default function EditValue({ displayname }: TEditField) {
  return (
    <div>
      <label htmlFor="price" className="block text-sm font-semibold text-white leading-6">
        {displayname}
      </label>
      <div className="relative mt-2 rounded-md shadow-sm ">
        <div className="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-3">
          <span className="text-gray-500 sm:text-sm">$</span>
        </div>
        <input
          type="text"
          name="price"
          id="price"
          className="block w-full rounded-md border-0 py-1.5 pl-7 text-gray-900 focus:outline-none sm:text-sm sm:leading-5"
          placeholder="0.00"
        />
      </div>
    </div>
  );
}